package com.x.o2o.web.shopadmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.x.o2o.dto.ShopExecution;
import com.x.o2o.entity.Area;
import com.x.o2o.entity.PersonInfo;
import com.x.o2o.entity.Shop;
import com.x.o2o.entity.ShopCategory;
import com.x.o2o.enums.ShopStateEnum;
import com.x.o2o.exceptions.ShopOperationException;
import com.x.o2o.service.AreaService;
import com.x.o2o.service.ShopCategoryService;
import com.x.o2o.service.ShopService;
import com.x.o2o.util.CodeUtil;
import com.x.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
	@Autowired
	private ShopService shopService;
	@Autowired
	private ShopCategoryService shopCategoryService;
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value="/getshopbyid",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getShopById(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		Long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		if(shopId>-1){
			try{
				Shop shop = shopService.getByShopId(shopId);
				List<Area> areaList=areaService.getAreaList();
				modelMap.put("shop", shop);
				modelMap.put("areaList", areaList);
				modelMap.put("success", true);
			}catch(Exception e){
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
			}
		}else{
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty shopId");
		}
		return modelMap;
	}
	
	
	@RequestMapping(value="/getshopinitinfo",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getShopInitInfo(){
		Map<String,Object> modelMap = new HashMap<String,Object>();
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		List<Area> areaList = new ArrayList<Area>();
		try{
			shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
			areaList = areaService.getAreaList();
			modelMap.put("shopCategoryList", shopCategoryList);
			modelMap.put("areaList", areaList);
			modelMap.put("success", true);
		}catch(Exception e){
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
		}
		return modelMap;
	}
	
	@RequestMapping(value = "/registershop", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> registerShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if(!CodeUtil.checkVerifyCode(request)){
			modelMap.put("success", false);
			modelMap.put("errMsg", "�����˴������֤��");
			return modelMap;
		}
		// 1.���ܲ�ת����Ӧ�Ĳ���������������Ϣ�Լ�ͼƬ��Ϣ
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "�ϴ�ͼƬ����Ϊ��");
			return modelMap;
		}
		// 2.ע�����
		if (shop != null && shopImg != null) {
	
			PersonInfo owner = new PersonInfo();
			owner.setUserId(1L);
			shop.setOwner(owner);
			//ʵ���û���¼ �ٷų�
//			PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
//			shop.setOwner(owner);
			
			
//			File shopImgFile = new File(PathUtil.getImgBasePath() + ImageUtil.getRandomFileName());
//			try {
//				shopImgFile.createNewFile();
//			} catch (IOException e) {
//				modelMap.put("success", false);
//				modelMap.put("errMsg", e.getMessage());
//				return modelMap;
//			}
//			try {
//				inputStreamToFile(shopImg.getInputStream(), shopImgFile);
//			} catch (IOException e) {
//				modelMap.put("success", false);
//				modelMap.put("errMsg", e.getMessage());
//				return modelMap;
//			}
			ShopExecution se;
			try {
				se = shopService.addShop(shop,shopImg.getInputStream(),shopImg.getOriginalFilename());
				if (se.getState() == ShopStateEnum.CHECK.getState()) {
					modelMap.put("success", true);
					//���û����ò����ĵ����б�
					@SuppressWarnings("unchecked")
					List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
					if (shopList == null || shopList.size() == 0) {
					shopList = new ArrayList<Shop>();
					}
					shopList.add(se.getShop());
					request.getSession().setAttribute("shopList", shopList);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
			} catch (ShopOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}		
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "�����������Ϣ");
			return modelMap;
		}

		// 3.���ؽ��
	}

	
	
	@RequestMapping(value = "/modifyshop", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> modifyShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if(!CodeUtil.checkVerifyCode(request)){
			modelMap.put("success", false);
			modelMap.put("errMsg", "�����˴������֤��");
			return modelMap;
		}
		// 1.���ܲ�ת����Ӧ�Ĳ���������������Ϣ�Լ�ͼƬ��Ϣ
		String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
		ObjectMapper mapper = new ObjectMapper();
		Shop shop = null;
		try {
			shop = mapper.readValue(shopStr, Shop.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		CommonsMultipartFile shopImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
		}
		// 2.�޸ĵ�����Ϣ
		if (shop != null && shop.getShopId() != null) {
			ShopExecution se;
			try {
				if(shopImg==null){
					se = shopService.modifyShop(shop, null, null);
				}else{
					se = shopService.modifyShop(shop,shopImg.getInputStream(),shopImg.getOriginalFilename());
				}
				if (se.getState() == ShopStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
			} catch (ShopOperationException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}		
			return modelMap;
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "���������Id");
			return modelMap;
		}

		// 3.���ؽ��
	}

	
	
	
	
//	private static void inputStreamToFile(InputStream ins, File file) {
//		FileOutputStream os = null;
//		try {
//			os = new FileOutputStream(file);
//			int bytesRead = 0;
//			byte[] buffer = new byte[1024];
//			while ((bytesRead = ins.read(buffer)) != -1) {
//				os.write(buffer, 0, bytesRead);
//			}
//		} catch (Exception e) {
//			throw new RuntimeException("����inputStreamToFile�����쳣��" + e.getMessage());
//		} finally {
//			try {
//				if (os != null) {
//					os.close();
//				}
//				if (ins != null) {
//					ins.close();
//				}
//			} catch (IOException e) {
//				throw new RuntimeException("inputStreamToFile�ر�io�����쳣" + e.getMessage());
//			}
//		}
//	}
	
	
	
	@RequestMapping(value = "/getshoplist",method = RequestMethod.GET)
    @ResponseBody
    private Map<String , Object> getShopList(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<String,Object>();
        PersonInfo user = new PersonInfo();
        //��Ϊ���û���û���¼ ���������ø�Ĭ��ֵ ģ���û���¼
        user.setUserId(1L);
        user.setName("test");
        request.getSession().setAttribute("user", user);
        
        user = (PersonInfo) request.getSession().getAttribute("user");
        try {
            Shop shopCondition = new Shop();
            shopCondition.setOwner(user);//��Session�е�user��ȡ��
            ShopExecution se = shopService.getShopList(shopCondition, 0, 100);//һ������ഴ��һ����
            modelMap.put("shopList", se.getShopList());
            modelMap.put("user", user);
            modelMap.put("success", true);
        }catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
        }
        return modelMap;
        
    }
	
	/**
     *����session��ص�
     * @param request
     * @return
     */
    @RequestMapping(value="/getshopmanagementinfo", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getShopManagementInfo(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<String, Object>();
        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        //���shopId������
        if(shopId <= 0){
            //�жϵ�ǰ��û�е�¼
            Object currentShopObj = request.getSession().getAttribute("currentShop");
            //�����ǰ��û��shopId���룬Ҳû�е��̵�¼�Ļ������ض��򵽵����б�ҳ��
            if(currentShopObj == null){
                modelMap.put( "redirect", true );
                modelMap.put( "url", "/shopadmin/shoplist" );
            }else{
                //�����ǰ�е��̵�¼�Ļ�
                Shop currentShop = (Shop)currentShopObj;
                modelMap.put( "redirect", false );
                modelMap.put( "shopId", currentShop.getShopId());
            }
        }else{
            //�����ǰ��shopId
            Shop currentShop = new Shop();
            currentShop.setShopId(shopId);
            request.getSession().setAttribute("currentShop", currentShop);
            modelMap.put( "redirect", false );
        }
        return modelMap;
    }
 

}
