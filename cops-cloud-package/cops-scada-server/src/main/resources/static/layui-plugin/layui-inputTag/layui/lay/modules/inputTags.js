/*
* @Author: layui-2
* @Date:   2018-08-31 11:40:42
* @Last Modified by:   layui-2
* @Last Modified time: 2018-09-04 14:44:38
* @Last Modified by:   zzf
* @Last Modified time: 2019-04-04 09:25:08
*/
layui.define(['jquery', 'layer'], function (exports) {
    "use strict";
    var $ = layui.jquery, layer = layui.layer
        //字符常量
        , MOD_NAME = 'inputTags'
        ,regular_list = {
            mobile: /^1[3|4|5|7|8]\d{9}$/,
            email: /^[A-Za-z0-9._%-]+@([A-Za-z0-9-]+\.)+[A-Za-z]{2,4}$/,
            no_chinese: /^[A-Za-z0-9]{4,40}$/,
            getResular: function (key) {
                if (this[key]) return this[key];
                return false;
            }
        }
        , inputTags= { }
        // 构造器
        , Class = function (options) {

            var tmp_content = options.content ? new Set(options.content) : [];
            options.content = [...tmp_content];
            var that = this;
            that.config = $.extend({}, that.config, options);
            that.proxy = {};
            that.AddTags(that.config.content);
        };
    Class.prototype.config = {
        theme :'#1e9fff' // 标签样式
        ,elem :'#tags'//绑定的 id 或者 class
        ,inputType : 'text'
        ,placeholder :  '回车生成标签'
        ,regular : {} // 正则验证
        // 回调事件
        , beforeEnter:function () { return true; } // 回车前事件
        , afterEnter: function (tags) { console.log(tags) } // 回车回调
        , content:[]
    };
    Class.prototype.on = function (events, callback) {
        var self = this;
        self.proxy[events] = callback;
    };
    Class.prototype.getTagsHtml = function (content) {
        var self = this;
        return "<div class='inputTags--tag' style='background:" + self.config.theme + ";'><span>" + content + "</span><span class='icon'>×</span></div>";
    };
    Class.prototype.init = function () {
        var self = this,
            inputTags_input = "<input class='inputTags--input layui-input' type='" + self.config.inputType + "' placeholder='" + self.config.placeholder + "' autocomplete='off'>",
            tags_html = "";
        // 设置主色调
        $(self.config.elem + " .inputTags--tag").css('background', '#FFB800');
        // 添加容器样式  添加主input框
        $(self.config.elem).addClass('inputTags--container').html(inputTags_input);

        // 循环添加标签 
        self.config.content.forEach((v, k) => {
            tags_html += self.getTagsHtml(v);
        });
        $(self.config.elem + " input").before(tags_html);

        // 监听事件
        self.bindEvent();
        self.bindDelTag();
    }
    Class.prototype.AddTag = function (value) {
        var self = this;
        if (self.config.content.indexOf(value) == -1) {
            $(self.config.elem + " input").before(self.getTagsHtml(value));
            self.config.content.push(value);
        }
        // 清空input内容
        $(self.config.elem + " input").val('');
    }
    Class.prototype.AddTags = function (values) {
        var self = this;
        if (values&&values.length>0) {
            values.forEach((v, k) => {
                if (v != '' && self.config.content.indexOf(v) == -1) {
                    // 添加标签
                    $(self.config.elem + " input").before(self.getTagsHtml(v));
                    self.config.content.push(v);
                }
            });
        }
        // 清空input内容
        $(self.config.elem + " input").val('');
    }
    Class.prototype.bindEvent = function () {
        var self = this;
        $(self.config.elem + " input").keypress(function (e) {
            var keynum = e.keyCode ? e.keyCode : e.which;
            if (keynum == '13') {
                // 生成标签前
                if (self.config.beforeEnter && self.config.beforeEnter() == false) {
                    return false;
                }

                // 数据初始化
                var input_obj = this;
                var input_value = $(input_obj).val().trim();

                // 正则验证--如果有执行验证
                if (self.config.regular.rule) {
                    var test_result = self.verification(input_value);
                    if (!test_result) return test_result;
                }
                self.AddTag(input_value);
                // 生成标签回调
                self.config.afterEnter && self.config.afterEnter(self.config.content);
            }
        });
    };
    Class.prototype.bindDelTag = function () {
        var self = this;
        $(self.config.elem).on('click', '.inputTags--tag', function (obj) {
            self.delTag(this);
        });
    };
    Class.prototype.delTag = function (obj) {
        var self = this;
        var del_text = $(obj).find('span').text(),
            del_index = self.config.content.indexOf(del_text);
        // 删除索引
        self.config.content.splice(del_index, 1);
        // 删除标签
        $(obj).remove();
        // 订阅者模式
        self.proxy['delTag'] && self.proxy['delTag'](self.config.content);
    };
    Class.prototype.clearAll = function () {
        var self = this;
        self.config.content = [];
        $(self.config.elem + " .inputTags--tag").remove();
    };
    Class.prototype.reload = function (conetnt) {
        var self = this;
        var con = conetnt ?new Set(conetnt) : [];
        // 先清空
        self.clearAll();
        // 渲染
        var values = [...con];
        self.AddTags(values);
    }

    Class.prototype.getValue = function () {
        var self = this;
        return self.config.content;
    }
    Class.prototype.verification = function (value) {
        var self = this,
            test_resule = false,
            rule = self.config.regular.rule;
        // 转换内置正则
        if (typeof rule == 'string') {
            rule = regular_list.getResular(rule);
            if (!rule) {
                self.config.regular.err_callback && self.config.regular.err_callback({ code: 2, msg: '正则表达式有误' });
                return false;
            }
        }
        if (rule.test) {
            rule.test(value) ? test_resule = true : self.config.regular.err_callback && self.config.regular.err_callback({ code: 1, msg: '未通过正则验证' });
        } else {
            self.config.regular.err_callback && self.config.regular.err_callback({ code: 2, msg: '正则表达式有误' });
        }
        return test_resule;
    }
    //核心入口
    inputTags.render = function (options) {
        var that =new Class(options);
        that.init();
        return that;
    };
    exports('inputTags', inputTags);
})