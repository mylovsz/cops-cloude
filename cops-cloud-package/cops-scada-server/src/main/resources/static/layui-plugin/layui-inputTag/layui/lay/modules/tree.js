/**
 @Name：layui.tree 树组件
 @Author：zzf
 @License：MIT
 */

layui.define('jquery', function (exports) {
    "use strict";
    var $ = layui.$
        , hint = layui.hint()
        , dataMap = {
            id:'id',
            parentid:'parentid',
            displayName: 'displayname',
            spread:'spread'
        }

    var enterSkin = 'layui-tree-enter', Tree = function (options) {
        this.options = options;
    };
    //图标
    var icon = {
        arrow: ["&#xe623;", "&#xe625;"],//箭头  --图标
        checkbox: ["&#xe626;", "&#xe627;"],
        radio: ["&#xe62b;", "&#xe62a;"],
        branch: ["&#xe62e;", "&#xe62e;"],//一级菜单
        leaf: "&#xe609;"//其他菜单
    };

    //初始化
    Tree.prototype.init = function (elem) {
        var that = this;
        elem.addClass('layui-box layui-tree'); //添加tree样式
        if (that.options.skin) {
            elem.addClass('layui-tree-skin-' + that.options.skin);
        }
        that.tree(elem);
        that.options.done(that.selectedItem);
    };
    //树节点解析
    Tree.prototype.tree = function (elem, parent) {
        var that = this, options = that.options,nodes = options.nodes;
        dataMap = options.dataMap;
        var currentNodes = nodes.filter(function (e) { return e[dataMap.parentid] == parent; });
        if (parent === null || parent === undefined || parent.length < 1) {
            currentNodes = nodes.filter(function (e)
            {
                return e[dataMap.parentid] == options.firstParent;
            });
        }
        
        layui.each(currentNodes, function (index, item) {
            if (item[dataMap.spread] === undefined) {
                item[dataMap.spread] = true;
            }
            var childs = nodes.filter(function (e) { return e[dataMap.parentid] == item[dataMap.id]; });
            var hasChild = childs && childs.length > 0;
            var ul = $('<ul class="' + (item[dataMap.spread] ? "layui-show" : "") + '"></ul>');
            var classSelected = '';
            if (that.selectedItem === null && (that.options.selectedItem === undefined || that.options.selectedItem[dataMap.id] === undefined)) {
                classSelected = 'class="layui-this"';
                that.selectedItem = item;
            }
            else {
                if (that.options.selectedItem != undefined && that.options.selectedItem[dataMap.id] && item[dataMap.id] === that.options.selectedItem[dataMap.id]) {
                    classSelected = 'class="layui-this"';
                    that.selectedItem = item;
                }
            }
            var li = $(['<li ' + (item[dataMap.spread] ? 'data-spread="' + item[dataMap.spread] + '"' : '') + ' ' + classSelected + '>'
                //展开箭头
                ,function () {
                    return hasChild ? '<i class="layui-icon layui-tree-spread">' + (
                        item[dataMap.spread] ? icon.arrow[1] : icon.arrow[0]
                    ) + '</i>' : '';
                }()

                //复选框/单选框
                , function () {
                    return options.check ? (
                        '<i class="layui-icon layui-tree-check">' + (
                            options.check === 'checkbox' ? icon.checkbox[0] : (
                                options.check === 'radio' ? icon.radio[0] : ''
                            )
                        ) + '</i>'
                    ) : '';
                }()

                //节点
                , function () {
                    return '<a href="' + (item.href || 'javascript:;') + '" ' + (
                        options.target && item.href ? 'target=\"' + options.target + '\"' : ''
                    ) + '>'
                        + ('<i class="layui-icon layui-tree-' + (hasChild ? "branch" : "leaf") + '">' + (
                            hasChild ? (
                            item[dataMap.spread] ? icon.branch[1] : icon.branch[0]
                            ) : icon.leaf
                        ) + '</i>') //节点图标
                        + ('<cite>' + (item[dataMap.displayName] || '未命名') + '</cite></a>');
                }()

                , '</li>'].join(''));

            //如果有子节点，则递归继续生成树
            if (hasChild) {
                li.append(ul);
                that.tree(ul, item[dataMap.id]);
            }
            elem.append(li);
            //触发点击节点回调
            typeof options.click === 'function' && that.click(li, item);
            //伸展节点
            that.spread(li, item);
            //拖拽节点
            options.drag && that.drag(li, item);
        });
        
    };
    //点击节点回调
    Tree.prototype.click = function (elem, item) {
        var that = this, options = that.options;
        elem.children('a').on('click', function (e) {
            layui.stope(e);
            layui.$(options.elem).find('li').removeClass('layui-this');
            layui.$(this).parent('li').addClass('layui-this');
            that.selectedItem = item;
            options.click(item)
        });
    };
    Tree.prototype.done = function (item) {
       
    }
    Tree.prototype.selectedItem = null;
    //伸展节点
    Tree.prototype.spread = function (elem, item) {
        var that = this, options = that.options;
        var arrow = elem.children('.layui-tree-spread')
        var ul = elem.children('ul'), a = elem.children('a');

        //执行伸展
        var open = function () {
            if (elem.data('spread')) {
                elem.data('spread', null)
                ul.removeClass('layui-show');
                arrow.html(icon.arrow[0]);
                a.find('.layui-icon').html(icon.branch[0]);
            } else {
                elem.data('spread', true);
                ul.addClass('layui-show');
                arrow.html(icon.arrow[1]);
                a.find('.layui-icon').html(icon.branch[1]);
            }
        };

        //如果没有子节点，则不执行
        if (!ul[0]) return;

        arrow.on('click', open);
        a.on('dblclick', open);
    }
    //暴露接口
    exports('tree', function (options) {
        var tree = new Tree(options = options || {});
        var elem = $(options.elem);
        if (!elem[0]) {
            return hint.error('layui.tree 没有找到' + options.elem + '元素');
        }
        tree.init(elem);
    });
});