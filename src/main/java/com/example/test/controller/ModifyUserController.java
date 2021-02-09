package com.example.test.controller;

import com.example.test.bean.UserBean;
import com.example.test.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ModifyUserController")
public class ModifyUserController {

    @Autowired
    UserService userService;

    /**
     * 查询所有用户信息
     * @param modelMap
     * @return
     */
    @RequestMapping("/users")
    public String showUsers(ModelMap modelMap){
        List<UserBean> userList = userService.queryAllUser("");
        modelMap.addAttribute("userList",userList);
        return "users";
    }

     /**
     * 根据条件查询用户信息
     * @param
     * @return json
     */
    @ResponseBody
    @RequestMapping(value="/getShowUsers",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public  Map getShowUsers(String id){
        if(id == "" || id ==null){
            id = "";
        }
        List<UserBean> userList = userService.queryAllUser(id);
        Map<String,Object> map = new HashMap<>();

        map.put("msg","成功");
        map.put("data",userList);
        return map;
    }

    /**
     * 新增数据,以map方式接收,json对象{"":"","":[]}
     * data:  JSON.stringify({
     *  "userId": "1",
     *  "userPw":"2",
     *  'master': JSON.stringify(param),
     *  'slave': JSON.stringify(slave),
     * })
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/addMapUsers",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map addMapUsers(@RequestBody Map<String, String> map){
        String userId = map.containsKey("userId") ? map.get("userId") : "" ;
        String userPw = map.containsKey("userPw") ? map.get("userPw") : "" ;
        String master = map.containsKey("master") ? map.get("master") : "" ;
        String slave = map.containsKey("slave") ? map.get("slave") : "" ;

        JSONObject masterJsonObject = JSONObject.fromObject(master);
        JSONArray slaveJsonArray = JSONArray.fromObject(slave);

        Map<String,Object> map1 = new HashMap<>();
        map1.put("msg","成功");

        return map1;
    }

    /**
     * 新增数据,以JSONObject方式接收,json对象{"key1":"","key2":[]}
     * data:  JSON.stringify({
     *  "userId": "1",
     *  "userPw":"2",
     *  'master': JSON.stringify(param),
     *  'slave': JSON.stringify(slave),
     * })
     * @param jsonObject
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/addJSONObjectUsers",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map addJSONObjectUsers(@RequestBody JSONObject jsonObject){
        String userId = jsonObject.has("userId") ? jsonObject.getString("userId"): "";
        String userPw = jsonObject.has("userPw") ? jsonObject.getString("userPw"): "";
        JSONObject master = JSONObject.fromObject(jsonObject.has("master") ? jsonObject.getString("master"): "");
        JSONArray slave = JSONArray.fromObject(jsonObject.getString("slave"));

        Map<String,Object> map1 = new HashMap<>();
        map1.put("msg","成功");

        return map1;
    }



    /**
     * 新增数据,以JSONArray方式接收,json对象[{"key1":"","uss":""},{"key2",""}]
     * data:  JSON.stringify(slve)
     * @param jsonArray
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/addJSONArrayUsers",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map addJSONArrayUsers(@RequestBody JSONArray jsonArray){
        StringBuffer sBuffer = new StringBuffer();


        Map<String,Object> map1 = new HashMap<>();
        map1.put("msg","成功");
        map1.put("result",sBuffer);
        return map1;
    }




    /**
     * 新增数据,以实体类方式接收,json对象{"":"","":""}
     * data:{
     *   "name": name,
     *   "password": password,
     *   "role": role
     *  }
     * remark：这里未做用户名是否重复校验
     * @param userBean
     * @return
     */
    @RequestMapping("/addUser")
    @ResponseBody
    public Map addUser(UserBean userBean){
        int flag = userService.addUser(userBean);
        Map<String,Object> map = new HashMap<String,Object>();
        if(flag == 1){
            map.put("msg","新增用户成功");
            return map;
        }else {
            map.put("msg","新增用户失败");
            return map;
        }
    }


    /**
     * 新增数据,以map方式接收,json对象[{"key1":"","uss":""},{"key2",""}]
     * data:  JSON.stringify(slve)
     * @param list
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/addListUsers",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map addListUsers(@RequestBody List<UserBean> list){
        String result = "";
        for(UserBean user:list){
            result += user.getName() +"\n";
        }

        Map<String,Object> map1 = new HashMap<>();
        map1.put("msg","成功");
        map1.put("result",result);
        return map1;
    }



    /**
     * 根据用户ID删除用户信息
     * @param id
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/deleteUser+{id}")
    public String dropUser(@PathVariable("id") String id, ModelMap modelMap){
        int flag = userService.dropUser(id);
        List<UserBean> userList = userService.queryAllUser(id);
        modelMap.addAttribute("userList",userList);
        if(flag == 1){
            return "users";
        }else {
            return "error";
        }
    }

    /**
     * 根据用户ID查询用户信息
     * @param id
     * @return
     */
    @RequestMapping("/user+{id}")
    public String queryUser(@PathVariable("id") String id,ModelMap modelMap){
        UserBean userBean = userService.queryUserById(id);
        modelMap.addAttribute("user",userBean);
        return "userInfo";
    }

    /**
     * 根据用户ID修改用户信息
     * remark：这里未做用户名是否重复校验
     * @param userBean
     * @return
     */
    @RequestMapping("/modifyUser")
    @ResponseBody
    public Map modifyUser(UserBean userBean){
        int flag = userService.modifyUser(userBean);
        Map<String,Object> map = new HashMap<>();
        if(flag == 1){
            map.put("msg","修改用户信息成功");
            return map;
        }else {
            map.put("msg","修改用户信息失败");
            return map;
        }
    }
}
