package com.xc.financial.mainapp;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.xc.financial.beans.AddressBean;
import com.xc.financial.beans.CodeDictBean;
import com.xc.financial.beans.UserBean;
import com.xc.financial.beans.UserSearchBean;
import com.xc.financial.enums.CodeDictEnum;
import com.xc.financial.enums.SexEnum;
import com.xc.financial.enums.SnTypeEnum;
import com.xc.financial.enums.StatusEnum;
import com.xc.financial.mapper.CodeDictMapper;
import com.xc.financial.mapper.SnMapper;
import com.xc.financial.mapper.UserMapper;
import com.xc.financial.utils.CollectionUtils;
import com.xc.financial.utils.DateUtils;
import com.xc.financial.utils.NumberFormatUtils;
import com.xc.financial.utils.ObjectUtils;
import com.xc.financial.utils.StringUtils;
import com.xc.financial.tools.*;

public class ManageUserFrame implements ActionListener{
	
	private JFrame frame;
	private JLabel infoArea,id,name,status,code,sex,username,phone,email,addressArea,province,city,town,address,houseCode,zipCode,otherArea,comments,birth;
	private JTextField idField,nameField,codeField,usernameField,phoneField,emailField,addressField,houseCodeField,zipCodeField,commentsField,birthField;
	private MyComboBox statusField,sexField,provinceField,cityField,townField;
	private List<Map<String,Object>> str = new ArrayList<Map<String,Object>>();
	private List<Map<String,Object>> sexstr = new ArrayList<Map<String,Object>>();
	private List<Map<String,Object>> provinceList = new ArrayList<Map<String,Object>>();
	private List<Map<String,Object>> cityList = new ArrayList<Map<String,Object>>();
	private List<Map<String,Object>> townList = new ArrayList<Map<String,Object>>();
	private Map<String,Object> black = new HashMap<String,Object>();
	private CodeDictMapper codeDictMapper = new CodeDictMapper();
	private UserMapper userkMapper = new UserMapper();
	private SnMapper snMapper = new SnMapper();
	private JPanel addressGrounp,infoGrounp,otherGrounp;
	private JButton save,cancel;
	private String operate;
	private Integer address_id;
	private DatePicker datepicker;
	private UserFrame userFrame;
	
	public ManageUserFrame(final UserFrame userFrame,String userCode,String operate){
		this.operate = operate;
		this.userFrame = userFrame;
		frame = new JFrame();
		frame.setLayout(null);
		init();
		
		infoArea = new JLabel(" 基本信息 ");
		infoArea.setFont(new Font("宋体", Font.PLAIN, 13));
		infoArea.setForeground(new Color(192, 192, 192));
		infoArea.setBounds(new Rectangle(25,5,70,20));
		infoArea.setBackground(Color.white);
		infoArea.setOpaque(true);
		
		infoGrounp = new JPanel();
		infoGrounp.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204), 1));
		infoGrounp.setBounds(new Rectangle(5,15,545,140));
		infoGrounp.setOpaque(false);
		
		id = new JLabel("编号：");
		id.setFont(new Font("宋体", Font.PLAIN, 14));
		id.setBounds(new Rectangle(30,30,45,20));
		
		idField = new JTextField();
		idField.setBounds(new Rectangle(70,30,120,20));
		idField.setOpaque(false);
		idField.setEditable(false);
		
		name = new JLabel("姓名：");
		name.setFont(new Font("宋体", Font.PLAIN, 14));
		name.setBounds(new Rectangle(205,30,45,20));
		
		nameField = new JTextField();
		nameField.setBounds(new Rectangle(245,30,120,20));
		
		status = new JLabel("状态：");
		status.setFont(new Font("宋体", Font.PLAIN, 14));
		status.setBounds(new Rectangle(380,30,45,20));
		
		statusField = new MyComboBox(str);
		statusField.setFont(new Font("宋体", Font.PLAIN, 13));
		statusField.setBounds(new Rectangle(420,30,100,20));
		statusField.setBackground(Color.white);
		statusField.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		code = new JLabel("编码：");
		code.setFont(new Font("宋体", Font.PLAIN, 14));
		code.setBounds(new Rectangle(30,60,45,20));
		
		codeField = new JTextField();
		codeField.setBounds(new Rectangle(70,60,120,20));
		codeField.setOpaque(false);
		codeField.setEditable(false);
		
		sex = new JLabel("性别：");
		sex.setFont(new Font("宋体", Font.PLAIN, 14));
		sex.setBounds(new Rectangle(205,60,45,20));
		
		sexField = new MyComboBox(sexstr);
		sexField.setFont(new Font("宋体", Font.PLAIN, 13));
		sexField.setBounds(new Rectangle(245,60,100,20));
		sexField.setBackground(Color.white);
		sexField.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		username = new JLabel("用户名：");
		username.setFont(new Font("宋体", Font.PLAIN, 14));
		username.setBounds(new Rectangle(15,90,60,20));
		
		usernameField = new JTextField();
		usernameField.setBounds(new Rectangle(70,90,120,20));
		
		phone = new JLabel("电话：");
		phone.setFont(new Font("宋体", Font.PLAIN, 14));
		phone.setBounds(new Rectangle(205,90,45,20));
		
		phoneField = new JTextField();
		phoneField.setBounds(new Rectangle(245,90,120,20));
		
		email = new JLabel("邮箱：");
		email.setFont(new Font("宋体", Font.PLAIN, 14));
		email.setBounds(new Rectangle(380,90,45,20));
		
		emailField = new JTextField();
		emailField.setBounds(new Rectangle(420,90,120,20));
		
		birth = new JLabel("生日：");
		birth.setFont(new Font("宋体", Font.PLAIN, 14));
		birth.setBounds(new Rectangle(30,120,45,20));
		
		birthField = new JTextField();
		birthField.setBounds(new Rectangle(70,120,175,20));
		datepicker = DatePicker.getInstance("yyyy-MM-dd");
		datepicker.register(birthField);
		
		addressArea = new JLabel(" 地址区域 ");
		addressArea.setFont(new Font("宋体", Font.PLAIN, 13));
		addressArea.setForeground(new Color(192, 192, 192));
		addressArea.setBounds(new Rectangle(25,160,70,20));
		addressArea.setBackground(Color.white);
		addressArea.setOpaque(true);
		
		addressGrounp = new JPanel();
		addressGrounp.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204), 1));
		addressGrounp.setBounds(new Rectangle(5,170,545,120));
		addressGrounp.setOpaque(false);
		
		province = new JLabel("省份：");
		province.setFont(new Font("宋体", Font.PLAIN, 14));
		province.setBounds(new Rectangle(30,190,45,20));
		
		provinceField = new MyComboBox(provinceList);
		provinceField.setFont(new Font("宋体", Font.PLAIN, 13));
		provinceField.setBounds(new Rectangle(70,190,100,20));
		provinceField.setBackground(Color.white);
		provinceField.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		city = new JLabel("市/区：");
		city.setFont(new Font("宋体", Font.PLAIN, 14));
		city.setBounds(new Rectangle(200,190,50,20));
		
		cityField = new MyComboBox(cityList);
		cityField.setFont(new Font("宋体", Font.PLAIN, 13));
		cityField.setBounds(new Rectangle(245,190,100,20));
		cityField.setBackground(Color.white);
		cityField.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		town = new JLabel("县/区：");
		town.setFont(new Font("宋体", Font.PLAIN, 14));
		town.setBounds(new Rectangle(375,190,50,20));
		
		townField = new MyComboBox(townList);
		townField.setFont(new Font("宋体", Font.PLAIN, 13));
		townField.setBounds(new Rectangle(420,190,100,20));
		townField.setBackground(Color.white);
		townField.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		address = new JLabel("地址：");
		address.setFont(new Font("宋体", Font.PLAIN, 14));
		address.setBounds(new Rectangle(30,220,45,20));
		
		addressField = new JTextField();
		addressField.setBounds(new Rectangle(70,220,276,20));
		
		houseCode = new JLabel("门牌号：");
		houseCode.setFont(new Font("宋体", Font.PLAIN, 14));
		houseCode.setBounds(new Rectangle(368,220,60,20));
		
		houseCodeField = new JTextField();
		houseCodeField.setBounds(new Rectangle(420,220,100,20));
		
		zipCode = new JLabel("邮编：");
		zipCode.setFont(new Font("宋体", Font.PLAIN, 14));
		zipCode.setBounds(new Rectangle(30,250,45,20));
		
		zipCodeField = new JTextField();
		zipCodeField.setBounds(new Rectangle(70,250,120,20));
		
		otherArea = new JLabel(" 其他信息 ");
		otherArea.setFont(new Font("宋体", Font.PLAIN, 13));
		otherArea.setForeground(new Color(192, 192, 192));
		otherArea.setBounds(new Rectangle(25,300,70,20));
		otherArea.setBackground(Color.white);
		otherArea.setOpaque(true);
		
		otherGrounp = new JPanel();
		otherGrounp.setBorder(BorderFactory.createLineBorder(new Color(204, 204, 204), 1));
		otherGrounp.setBounds(new Rectangle(5,310,545,70));
		otherGrounp.setOpaque(false);
		
		comments = new JLabel("备注：");
		comments.setFont(new Font("宋体", Font.PLAIN, 14));
		comments.setBounds(new Rectangle(30,330,45,20));
		
		commentsField = new JTextField();
		commentsField.setBounds(new Rectangle(70,330,276,20));
		
		save = new JButton("保存");
        save.setCursor(new Cursor(Cursor.HAND_CURSOR));
        save.setBounds(new Rectangle(200,390,60,25));
        cancel = new JButton("取消");
        cancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancel.setBounds(new Rectangle(270,390,60,25));
        
        save.addActionListener(this);
        cancel.addActionListener(this);
        provinceField.addActionListener(this);
        cityField.addActionListener(this);
        
		//基本信息
		frame.add(infoArea);
		frame.add(id);
		frame.add(idField);
		frame.add(name);
		frame.add(nameField);
		frame.add(status);
		frame.add(statusField);
		frame.add(code);
		frame.add(codeField);
		frame.add(sex);
		frame.add(sexField);
		frame.add(username);
		frame.add(usernameField);
		frame.add(phone);
		frame.add(phoneField);
		frame.add(email);
		frame.add(emailField);
		frame.add(birth);
		frame.add(birthField);
		frame.add(infoGrounp);
		//地址区域
		frame.add(addressArea);
		frame.add(province);
		frame.add(provinceField);
		frame.add(city);
		frame.add(cityField);
		frame.add(town);
		frame.add(townField);
		frame.add(address);
		frame.add(addressField);
		frame.add(houseCode);
		frame.add(houseCodeField);
		frame.add(zipCode);
		frame.add(zipCodeField);
		frame.add(addressGrounp);
		//其他信息
		frame.add(otherArea);
		frame.add(comments);
		frame.add(commentsField);
		frame.add(otherGrounp);
		
		frame.add(save);
		frame.add(cancel);
		
		frame.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getSource() != datepicker){
					datepicker.hidePanel();
				}
			}
		});
		
		getDatas(userCode);
		doLay();
		frame.setSize(560,450);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter(){
			@SuppressWarnings("deprecation")
			@Override
			public void windowClosing(WindowEvent e){
				frame.dispose();
				userFrame.getFrame().setEnabled(true);
				userFrame.getFrame().show();
			}
		});
	}
	
	private void doLay() {
        Container container = frame.getContentPane();
        container.setBackground(Color.white);
        frame.pack();
    } 
	
	private void init(){
		//初始化静态数据
		str.clear();
		black.put("label", "请选择...");
		black.put("value", null);
		str.add(black);
		
		Map<String,Object> item = new HashMap<String,Object>();
		item.put("label", "正常");
		item.put("value", "Y");
		str.add(item);
		
		Map<String,Object> item1 = new HashMap<String,Object>();
		item1.put("label", "不正常");
		item1.put("value", "N");
		str.add(item1);
		
		sexstr.clear();
		sexstr.add(black);
		
		Map<String,Object> item2 = new HashMap<String,Object>();
		item2.put("label", "男");
		item2.put("value", 0);
		sexstr.add(item2);
		
		Map<String,Object> item3 = new HashMap<String,Object>();
		item3.put("label", "女");
		item3.put("value", 1);
		sexstr.add(item3);
		
		provinceList.clear();
		provinceList.add(black);
		cityList.clear();
		cityList.add(black);
		townList.clear();
		townList.add(black);
		
	}
	
	private void saveUpdateData(String operate){
		UserBean userBean = prepareUserBean();
		if(ObjectUtils.isNotEmpty(userBean)){
			try {
				if("add".equals(operate)){
					int num = snMapper.selectSn(SnTypeEnum.USER_CODE.getKey());
					userBean.setCode("F" + DateUtils.parseDates(new Date()) + NumberFormatUtils.formatNumber(num));
					userkMapper.insertUser(userBean);
					JOptionPane.showMessageDialog(null, "添加成功！");
				}else{
					userkMapper.updateUser(userBean);
					JOptionPane.showMessageDialog(null, "更新成功！");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else{
			JOptionPane.showMessageDialog(null, "请填必填项！");
		}
	}
	
	private UserBean prepareUserBean() {
		UserBean userBean = new UserBean();
		try{
			userBean.setId(Integer.parseInt(idField.getText()));
			userBean.setName(nameField.getText());
			userBean.setStatus(statusField.getSelectedItemValue().toString());
			userBean.setCode(codeField.getText());
			userBean.setSex(StringUtils.isEmpty(sexField.getSelectedItemValue())? null:Integer.parseInt(sexField.getSelectedItemValue().toString()));
			userBean.setUsername(usernameField.getText());
			userBean.setPhone(phoneField.getText());
			userBean.setEmail(emailField.getText());
			userBean.setBirth(birthField.getText());
			userBean.setPassword("12345678");
			userBean.setCreateDate(DateUtils.parseLongDate(new Date()));
			userBean.setModifyDate(DateUtils.parseLongDate(new Date()));
			userBean.setComments(commentsField.getText());
			userBean.setOperate(null);
			//构建address类
			AddressBean addressBean = new AddressBean();
			addressBean.setId(address_id);
			addressBean.setCountryCode("832");
			addressBean.setProvinceCode(StringUtils.isEmpty(provinceField.getSelectedItemValue())? null : provinceField.getSelectedItemValue().toString());
			addressBean.setCityCode(StringUtils.isEmpty(cityField.getSelectedItemValue())? null : cityField.getSelectedItemValue().toString());
			addressBean.setTownCode(StringUtils.isEmpty(townField.getSelectedItemValue())? null : townField.getSelectedItemValue().toString());
			addressBean.setAddr(addressField.getText());
			addressBean.setHouseCode(houseCodeField.getText());
			addressBean.setZipCode(zipCodeField.getText());
			userBean.setAddressBean(addressBean);
		}catch(ParseException e){
			e.printStackTrace();
		}
		return userBean;
	}
	
	private void getDatas(String userCode){
		UserBean userBean = userkMapper.selectUsersByCode(userCode);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("code", "832");
		params.put("type", CodeDictEnum.DISTRICT.getKey());
		List<CodeDictBean> provinces = codeDictMapper.selectChildrenByParams(params);
		if(CollectionUtils.isNotEmpty(provinces)){
			for(CodeDictBean codeDictBean : provinces){
				Map<String,Object> item = new HashMap<String,Object>();
				item.put("label", codeDictBean.getValue());
				item.put("value", codeDictBean.getCode());
				provinceList.add(item);
			}
			provinceField.refresh(); 
		}
		if(ObjectUtils.isNotEmpty(userBean)){
			initUserFrameData(userBean);
		}else{
			clearData();
		}
	}
	
	private void clearData(){
		idField.setText(null);
		nameField.setText(null);
		statusField.setSelectedItem(null);
		codeField.setText(null);
		sexField.setSelectedItem(null);
		usernameField.setText(null);
		birthField.setText(null);
		phoneField.setText(null);
		emailField.setText(null);
		commentsField.setText(null);
		address_id = null;
		provinceField.setSelectItem(null);
		cityList.clear();
		cityList.add(black);
		cityField.refresh();
		cityField.setSelectItem(null);
		townList.clear();
		townList.add(black);
		townField.refresh();
		townField.setSelectItem(null);
		addressField.setText(null);
		houseCodeField.setText(null);
		zipCodeField.setText(null);
	}
	
	private void initUserFrameData(UserBean userBean){
		idField.setText(String.valueOf(userBean.getId()));
		nameField.setText(userBean.getName());
		statusField.setSelectedItem(StatusEnum.getStatusValueByKey(userBean.getStatus()).getValue());
		codeField.setText(userBean.getCode());
		sexField.setSelectedItem(SexEnum.getSexValueByKey(userBean.getSex()).getValue());
		usernameField.setText(userBean.getUsername());
		phoneField.setText(userBean.getPhone());
		emailField.setText(userBean.getEmail());
		birthField.setText(userBean.getBirth());
		commentsField.setText(userBean.getComments());
		address_id = userBean.getAddressId();
		provinceField.setSelectItem(userBean.getAddressBean().getProvinceCode());
		cityField.setSelectItem(userBean.getAddressBean().getCityCode());
		townField.setSelectItem(userBean.getAddressBean().getTownCode());
		addressField.setText(userBean.getAddressBean().getAddr());
		houseCodeField.setText(userBean.getAddressBean().getHouseCode());
		zipCodeField.setText(userBean.getAddressBean().getZipCode());
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == save){
			frame.dispose();
			saveUpdateData(operate);
			UserSearchBean searchBean = new UserSearchBean();
			searchBean.setPageNumber(new Long(1));
			searchBean.setPageSize(new Long(15));
			userFrame.getDatas(searchBean);
			userFrame.getFrame().setEnabled(true);
			userFrame.getFrame().show();
		}
		
		if(e.getSource() == cancel){
			frame.dispose();
			userFrame.getFrame().setEnabled(true);
			userFrame.getFrame().show();
		}
		
		if(e.getSource() == provinceField){
			cityList.clear();
			cityList.add(black);
			townList.clear();
			townList.add(black);
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("code", provinceField.getSelectedItemValue());
			params.put("type", CodeDictEnum.DISTRICT.getKey());
			List<CodeDictBean> citys = codeDictMapper.selectChildrenByParams(params);
			if(CollectionUtils.isNotEmpty(citys)){
				for(CodeDictBean codeDictBean : citys){
					Map<String,Object> item = new HashMap<String,Object>();
					item.put("label", codeDictBean.getValue());
					item.put("value", codeDictBean.getCode());
					cityList.add(item);
				}
			}
			cityField.refresh();
		}
		
		if(e.getSource() == cityField){
			townList.clear();
			townList.add(black);
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("code", cityField.getSelectedItemValue());
			params.put("type", CodeDictEnum.DISTRICT.getKey());
			List<CodeDictBean> towns = codeDictMapper.selectChildrenByParams(params);
			if(CollectionUtils.isNotEmpty(towns)){
				for(CodeDictBean codeDictBean : towns){
					Map<String,Object> item = new HashMap<String,Object>();
					item.put("label", codeDictBean.getValue());
					item.put("value", codeDictBean.getCode());
					townList.add(item);
				}
			}
			townField.refresh();
		}
	}
	
//	public static void main(String[] args){
//		new ManageUserFrame(new UserFrame(),null,"add");
//	}
}
