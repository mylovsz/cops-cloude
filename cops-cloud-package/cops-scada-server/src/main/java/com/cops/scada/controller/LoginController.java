package com.cops.scada.controller;

import com.cops.scada.annotation.SysLog;
import com.cops.scada.base.BaseController;
import com.cops.scada.base.MySysUser;
import com.cops.scada.entity.Menu;
import com.cops.scada.entity.User;
import com.cops.scada.entity.VO.StatisticsExaminesCollect;
import com.cops.scada.entity.VO.StatisticsPlanVO;
import com.cops.scada.entity.VO.StatisticsProduceVO;
import com.cops.scada.entity.VO.StatisticsRateVO;
import com.cops.scada.service.*;
import com.cops.scada.util.Constants;
import com.cops.scada.util.RestResponse;
import com.cops.scada.util.VerifyCodeUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xiaoleilu.hutool.date.DateTime;
import com.xiaoleilu.hutool.date.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@Controller
public class LoginController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private ProduceService produceService;

	@Autowired
	private ExaminesAService examinesAService;

	@Autowired
	private ExaminesBService examinesBService;

	@Autowired
	private ExaminesCService examinesCService;

	@Autowired
	private ExaminesDService examinesDService;

	@Autowired
	private ExaminesEService examinesEService;

	@Autowired
	private ExaminesFService examinesFService;

	@Value("${server.port}")
	private String port;

	@GetMapping("login")
	public String login(HttpServletRequest request) {
		LOGGER.info("跳到这边的路径为:"+request.getRequestURI());
		Subject s = SecurityUtils.getSubject();
		LOGGER.info("是否记住登录--->"+s.isRemembered()+"<-----是否有权限登录----->"+s.isAuthenticated()+"<----");
		if(s.isAuthenticated()){
			return "redirect:index";
		}else {
			return "login";
		}
	}
	
	@PostMapping("login/main")
	@ResponseBody
	@SysLog("用户登录")
	public RestResponse loginMain(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rememberMe = request.getParameter("rememberMe");
		String code = request.getParameter("code");
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
			return RestResponse.failure("用户名或者密码不能为空");
		}
		if(StringUtils.isBlank(rememberMe)){
			return RestResponse.failure("记住我不能为空");
		}
		if(StringUtils.isBlank(code)){
			return  RestResponse.failure("验证码不能为空");
		}
		Map<String,Object> map = Maps.newHashMap();
		String error = null;
		HttpSession session = request.getSession();
		if(session == null){
			return RestResponse.failure("session超时");
		}
		String trueCode =  (String)session.getAttribute(Constants.VALIDATE_CODE);
		if(StringUtils.isBlank(trueCode)){
			return RestResponse.failure("验证码超时");
		}
		if(StringUtils.isBlank(code) || !trueCode.toLowerCase().equals(code.toLowerCase())){
			error = "验证码错误";
		}else {
			/*就是代表当前的用户。*/
			Subject user = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username,password,Boolean.valueOf(rememberMe));
			try {
				user.login(token);
				if (user.isAuthenticated()) {
					map.put("url","index");
					map.put("nickName",getCurrentUser().getNickName());
				}
			}catch (IncorrectCredentialsException e) {
				error = "登录密码错误.";
			} catch (ExcessiveAttemptsException e) {
				error = "登录失败次数过多";
			} catch (LockedAccountException e) {
				error = "帐号已被锁定.";
			} catch (DisabledAccountException e) {
				error = "帐号已被禁用.";
			} catch (ExpiredCredentialsException e) {
				error = "帐号已过期.";
			} catch (UnknownAccountException e) {
				error = "帐号不存在";
			} catch (UnauthorizedException e) {
				error = "您没有得到相应的授权！";
			}
		}
		if(StringUtils.isBlank(error)){
			return RestResponse.success("登录成功").setData(map);
		}else{
			return RestResponse.failure(error);
		}
	}
	
	@GetMapping("index")
	public String showView(Model model){
		return "index";
	}


	/**
	 * 获取验证码图片和文本(验证码文本会保存在HttpSession中)
	 */
	@GetMapping("/genCaptcha")
	public void genCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//设置页面不缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_NUM_ONLY, 4, null);
		//将验证码放到HttpSession里面
		request.getSession().setAttribute(Constants.VALIDATE_CODE, verifyCode);
		LOGGER.info("本次生成的验证码为[" + verifyCode + "],已存放到HttpSession中");
		//设置输出的内容的类型为JPEG图像
		response.setContentType("image/jpeg");
		BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 116, 36, 5, true, new Color(249,205,173), null, null);
		//写给浏览器
		ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
	}

	@GetMapping("home/collect")
	@ResponseBody
	public RestResponse collect(){
		// Examines a相关
		Date d = new Date();
		//Date old = new Date(d.getTime()-(long)29*24*60*60*1000);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -29);
		c.set(Calendar.AM_PM, 0);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		Date old = c.getTime();
		List<StatisticsExaminesCollect> list;
		List<Long> pv;
		RestResponse rr = RestResponse.success();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );

		List<String> dayList = Lists.newArrayList(); // 存放x轴的日期
		for (int i=-29;i<=0;i++){
			DateTime dateTime = DateUtil.offsetDay(d,i);
			dayList.add(dateTime.toString("yyyy-MM-dd"));
		}
		rr.setAny("dayList", dayList);

		list = examinesAService.getStatisticsExaminesCollect(old, d);
		pv = Lists.newArrayList();
		for (int i=0;i<dayList.size();i++){
			Long total = 0L;
			for(StatisticsExaminesCollect sec : list){
				String date  = sdf.format(sec.getCollectDate());
				total = sec.getCollectCount();
				if(date.equalsIgnoreCase(dayList.get(i))){
					break;
				}else{
					total = 0L;
				}
			}
			pv.add(total);
		}
		rr.setAny("collectA", pv);

		list = examinesBService.getStatisticsExaminesCollect(old, d);
		pv = Lists.newArrayList();
		for (int i=0;i<dayList.size();i++){
			Long total = 0L;
			for(StatisticsExaminesCollect sec : list){
				String date  = sdf.format(sec.getCollectDate());
				total = sec.getCollectCount();
				if(date.equalsIgnoreCase(dayList.get(i))){
					break;
				}else{
					total = 0L;
				}
			}
			pv.add(total);
		}
		rr.setAny("collectB", pv);

		list = examinesCService.getStatisticsExaminesCollect(old, d);
		pv = Lists.newArrayList();
		for (int i=0;i<dayList.size();i++){
			Long total = 0L;
			for(StatisticsExaminesCollect sec : list){
				String date  = sdf.format(sec.getCollectDate());
				total = sec.getCollectCount();
				if(date.equalsIgnoreCase(dayList.get(i))){
					break;
				}else{
					total = 0L;
				}
			}
			pv.add(total);
		}
		rr.setAny("collectC", pv);

		list = examinesDService.getStatisticsExaminesCollect(old, d);
		pv = Lists.newArrayList();
		for (int i=0;i<dayList.size();i++){
			Long total = 0L;
			for(StatisticsExaminesCollect sec : list){
				String date  = sdf.format(sec.getCollectDate());
				total = sec.getCollectCount();
				if(date.equalsIgnoreCase(dayList.get(i))){
					break;
				}else{
					total = 0L;
				}
			}
			pv.add(total);
		}
		rr.setAny("collectD", pv);

		list = examinesEService.getStatisticsExaminesCollect(old, d);
		pv = Lists.newArrayList();
		for (int i=0;i<dayList.size();i++){
			Long total = 0L;
			for(StatisticsExaminesCollect sec : list){
				String date  = sdf.format(sec.getCollectDate());
				total = sec.getCollectCount();
				if(date.equalsIgnoreCase(dayList.get(i))){
					break;
				}else{
					total = 0L;
				}
			}
			pv.add(total);
		}
		rr.setAny("collectE", pv);

		list = examinesFService.getStatisticsExaminesCollect(old, d);
		pv = Lists.newArrayList();
		for (int i=0;i<dayList.size();i++){
			Long total = 0L;
			for(StatisticsExaminesCollect sec : list){
				String date  = sdf.format(sec.getCollectDate());
				total = sec.getCollectCount();
				if(date.equalsIgnoreCase(dayList.get(i))){
					break;
				}else{
					total = 0L;
				}
			}
			pv.add(total);
		}
		rr.setAny("collectF", pv);

		return rr;
	}

	@GetMapping("home")
	public String home(Model model){
		Date debugDate = new Date();
		System.out.println("Home 开始访问："
				+ DateUtil.format(debugDate, "yyyy-MM-dd HH:mm:;ss"));

		// 工单相关的内容
		StatisticsPlanVO statisticsPlanVO = planService.getStatisticsPlanVO();
		model.addAttribute("statisticsPlanVO", statisticsPlanVO);

		// 产品相关的内容
		StatisticsProduceVO statisticsProduceVO = produceService.getStatisticsProduceVO();
		model.addAttribute("statisticsProduceVO", statisticsProduceVO);

		System.out.println("Home 结束访问："
				+ DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:;ss")
				+ "  耗时：" + (new Date().getTime() - debugDate.getTime())
		);
		return "home";
	}

	@PostMapping("hidHome/info")
	@ResponseBody
	public RestResponse hidHomeInfo(String strStartDate, String strEndDate){

		Date startDate, endDate;
		Calendar cal = Calendar.getInstance();

		try {
			endDate = DateUtil.parse(strEndDate, "yyyy-MM-dd HH:mm:ss");
		}
		catch (Exception e){
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			cal.set(Calendar.MILLISECOND, 0);
			endDate = cal.getTime();
		}

		try {
			startDate = DateUtil.parse(strStartDate, "yyyy-MM-dd HH:mm:ss");
		}
		catch (Exception e){
			cal.set(cal.get(Calendar.YEAR),0, 1);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			startDate = cal.getTime();
		}


		RestResponse restResponse = RestResponse.success();

		// 工单相关的内容
		StatisticsPlanVO statisticsPlanVO = planService.getStatisticsPlanVO(0, startDate, endDate);
		restResponse.setAny("statisticsPlanVO", statisticsPlanVO);
		// 产品相关的内容
		StatisticsProduceVO statisticsProduceVO = produceService.getStatisticsProduceVO(0, startDate, endDate);
		restResponse.setAny("statisticsProduceVO", statisticsProduceVO);
		// 计算各率
		StatisticsRateVO statisticsRateVO = new StatisticsRateVO();
		if(statisticsPlanVO.getTotalQuantity() != 0){
			statisticsRateVO.setProductCompleteRate(String.format("%.2f%%",
					statisticsProduceVO.getStatisticsSuccess()*100.0/statisticsPlanVO.getTotalQuantity()));
			statisticsRateVO.setProductDataCoverRate(String.format("%.2f%%",
					(statisticsPlanVO.getTotalQuantity() - statisticsProduceVO.getStatisticsReady())*100.0/statisticsPlanVO.getTotalQuantity()));
			if((statisticsPlanVO.getTotalQuantity() - statisticsProduceVO.getStatisticsReady()) != 0){
				statisticsRateVO.setProductErrorRate(String.format("%.2f%%",
						statisticsProduceVO.getStatisticsError()*100.0/(statisticsPlanVO.getTotalQuantity() - statisticsProduceVO.getStatisticsReady())));
			}
			else{
				statisticsRateVO.setProductErrorRate("0.00%");
			}

			statisticsRateVO.setProductPerfectRate(String.format("%.2f%%",
					statisticsProduceVO.getStatisticsPerfect()*100.0/statisticsPlanVO.getTotalQuantity()));
		}
		else{
			statisticsRateVO.setProductPerfectRate("0.00%");
			statisticsRateVO.setProductErrorRate("0.00%");
			statisticsRateVO.setProductDataCoverRate("0.00%");
			statisticsRateVO.setProductCompleteRate("0.00%");
		}
		statisticsRateVO.setStartDate(DateUtil.format(startDate, "yyyy-MM-dd"));
		statisticsRateVO.setEndDate(DateUtil.format(endDate, "yyyy-MM-dd"));
		restResponse.setAny("statisticsRateVO", statisticsRateVO);

		return restResponse;
	}

	@GetMapping("showHid")
	public String showHid(Model model){

		Date debugDate = new Date();
		System.out.println("Home 开始访问："
				+ DateUtil.format(debugDate, "yyyy-MM-dd HH:mm:;ss"));

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);

		Date endDate = cal.getTime();
		cal.set(cal.get(Calendar.YEAR),0, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date startDate = cal.getTime();

		// 工单相关的内容
		StatisticsPlanVO statisticsPlanVO = planService.getStatisticsPlanVO(0, startDate, endDate);
		model.addAttribute("statisticsPlanVO", statisticsPlanVO);

		// 产品相关的内容
		StatisticsProduceVO statisticsProduceVO = produceService.getStatisticsProduceVO(0, startDate, endDate);
		model.addAttribute("statisticsProduceVO", statisticsProduceVO);

		// 计算各率
		StatisticsRateVO statisticsRateVO = new StatisticsRateVO();
		if(statisticsPlanVO.getTotalQuantity()==null)
		{
			statisticsPlanVO.setTotalQuantity(0L);
		}
		if(statisticsPlanVO.getTotalQuantity() != 0){
			statisticsRateVO.setProductCompleteRate(String.format("%.2f%%",
					statisticsProduceVO.getStatisticsSuccess()*100.0/statisticsPlanVO.getTotalQuantity()));
			statisticsRateVO.setProductDataCoverRate(String.format("%.2f%%",
					(statisticsPlanVO.getTotalQuantity() - statisticsProduceVO.getStatisticsReady())*100.0/statisticsPlanVO.getTotalQuantity()));
			if((statisticsPlanVO.getTotalQuantity() - statisticsProduceVO.getStatisticsReady()) != 0){
				statisticsRateVO.setProductErrorRate(String.format("%.2f%%",
						statisticsProduceVO.getStatisticsError()*100.0/(statisticsPlanVO.getTotalQuantity() - statisticsProduceVO.getStatisticsReady())));
			}
			else{
				statisticsRateVO.setProductErrorRate("0.00%");
			}

			statisticsRateVO.setProductPerfectRate(String.format("%.2f%%",
					statisticsProduceVO.getStatisticsPerfect()*100.0/statisticsPlanVO.getTotalQuantity()));
		}
		else{
			statisticsRateVO.setProductPerfectRate("0.00%");
			statisticsRateVO.setProductErrorRate("0.00%");
			statisticsRateVO.setProductDataCoverRate("0.00%");
			statisticsRateVO.setProductCompleteRate("0.00%");
		}
		statisticsRateVO.setStartDate(DateUtil.format(startDate, "yyyy-MM-dd"));
		statisticsRateVO.setEndDate(DateUtil.format(endDate, "yyyy-MM-dd"));
		model.addAttribute("statisticsRateVO", statisticsRateVO);

		System.out.println("Home 结束访问："
				+ DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:;ss")
				+ "  耗时：" + (System.currentTimeMillis() - debugDate.getTime())
		);

		return "showHid";
	}

	@GetMapping("hidHome")
	public String hidHome(Model model){

		Date debugDate = new Date();
		System.out.println("Home 开始访问："
				+ DateUtil.format(debugDate, "yyyy-MM-dd HH:mm:;ss"));

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 0);

		Date endDate = cal.getTime();
		cal.set(cal.get(Calendar.YEAR),0, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date startDate = cal.getTime();

		// 工单相关的内容
		StatisticsPlanVO statisticsPlanVO = planService.getStatisticsPlanVO(0, startDate, endDate);
		model.addAttribute("statisticsPlanVO", statisticsPlanVO);

		// 产品相关的内容
		StatisticsProduceVO statisticsProduceVO = produceService.getStatisticsProduceVO(0, startDate, endDate);
		model.addAttribute("statisticsProduceVO", statisticsProduceVO);

		// 计算各率
		StatisticsRateVO statisticsRateVO = new StatisticsRateVO();
		if(statisticsPlanVO.getTotalQuantity()==null)
		{
			statisticsPlanVO.setTotalQuantity(0L);
		}
		if(statisticsPlanVO.getTotalQuantity() != 0){
			statisticsRateVO.setProductCompleteRate(String.format("%.2f%%",
					statisticsProduceVO.getStatisticsSuccess()*100.0/statisticsPlanVO.getTotalQuantity()));
			statisticsRateVO.setProductDataCoverRate(String.format("%.2f%%",
					(statisticsPlanVO.getTotalQuantity() - statisticsProduceVO.getStatisticsReady())*100.0/statisticsPlanVO.getTotalQuantity()));
			if((statisticsPlanVO.getTotalQuantity() - statisticsProduceVO.getStatisticsReady()) != 0){
				statisticsRateVO.setProductErrorRate(String.format("%.2f%%",
						statisticsProduceVO.getStatisticsError()*100.0/(statisticsPlanVO.getTotalQuantity() - statisticsProduceVO.getStatisticsReady())));
			}
			else{
				statisticsRateVO.setProductErrorRate("0.00%");
			}

			statisticsRateVO.setProductPerfectRate(String.format("%.2f%%",
					statisticsProduceVO.getStatisticsPerfect()*100.0/statisticsPlanVO.getTotalQuantity()));
		}
		else{
			statisticsRateVO.setProductPerfectRate("0.00%");
			statisticsRateVO.setProductErrorRate("0.00%");
			statisticsRateVO.setProductDataCoverRate("0.00%");
			statisticsRateVO.setProductCompleteRate("0.00%");
		}
		statisticsRateVO.setStartDate(DateUtil.format(startDate, "yyyy-MM-dd"));
		statisticsRateVO.setEndDate(DateUtil.format(endDate, "yyyy-MM-dd"));
		model.addAttribute("statisticsRateVO", statisticsRateVO);

		System.out.println("Home 结束访问："
				+ DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:;ss")
				+ "  耗时：" + (System.currentTimeMillis() - debugDate.getTime())
		);

		return "hidHome";
	}

	@GetMapping("ledHome")
	public String ledHome(Model model){

		Date debugDate = new Date();
		System.out.println("Home 开始访问："
				+ DateUtil.format(debugDate, "yyyy-MM-dd HH:mm:;ss"));

		// 工单相关的内容
		StatisticsPlanVO statisticsPlanVO = planService.getStatisticsLedPlanVO();
		model.addAttribute("statisticsPlanVO", statisticsPlanVO);

		// 产品相关的内容
		StatisticsProduceVO statisticsProduceVO = produceService.getStatisticsProduceVO(2);
		model.addAttribute("statisticsProduceVO", statisticsProduceVO);

		System.out.println("Home 结束访问："
				+ DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:;ss")
				+ "  耗时：" + (new Date().getTime() - debugDate.getTime())
		);

		return "ledHome";
	}

	@GetMapping("main")
	public String main(Model model){
		Map map = userService.selectUserMenuCount();
		User user = userService.findUserById(MySysUser.id());
		Set<com.cops.scada.entity.Menu> menus = user.getMenus();
		java.util.List<com.cops.scada.entity.Menu> showMenus = Lists.newArrayList();
		if(menus != null && menus.size()>0){
			for (Menu menu : menus){
				if(StringUtils.isNotBlank(menu.getHref())){
					Long result = (Long)map.get(menu.getPermission());
					if(result != null){
						menu.setDataCount(result.intValue());
						showMenus.add(menu);
					}
				}
			}
		}
		showMenus.sort(new MenuComparator());
		model.addAttribute("userMenu",showMenus);
		return "main";
	}

	/**
	 *  空地址请求
	 * @return
	 */
	@GetMapping(value = "")
	public String index() {
		LOGGER.info("这事空地址在请求路径");
		Subject s = SecurityUtils.getSubject();
		return s.isAuthenticated() ? "redirect:index" : "login";
	}

	@GetMapping("systemLogout")
	@SysLog("退出系统")
	public String logOut(){
		SecurityUtils.getSubject().logout();
		return "redirect:/login";
	}
}

class MenuComparator implements Comparator<com.cops.scada.entity.Menu>{

	@Override
	public int compare(com.cops.scada.entity.Menu o1, com.cops.scada.entity.Menu o2) {
		if(o1.getSort()>o2.getSort()){
			return -1;
		}else {
			return 0;
		}

	}
}