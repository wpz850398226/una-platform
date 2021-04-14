package cn.kunli.una.controller.activiti;

import cn.kunli.una.pojo.vo.SysResult;
import cn.kunli.una.pojo.vo.ActTask;
import cn.kunli.una.service.activiti.ActTaskService;
import cn.kunli.una.service.system.SysDictionaryService;
import cn.kunli.una.service.system.SysAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * activiti流程任务管理
 *
 * @author Ponzio
 * 时间：2020年3月4日07:24:07
 */
@Controller
@RequestMapping("/act/task")
public class ActTaskController {
    @Autowired
    private ActTaskService objService;
    @Autowired
    private SysDictionaryService sysDictionaryService;
    @Autowired
    private SysAccountService sysAccountService;


    /**
     * 拾取任务（组任务领取用）
     *
     * @param obj 流程变量工具类，存放流程变量，任务id
     * @return
     */
    @RequestMapping("claim")
    @ResponseBody
    public SysResult claim(ActTask obj) {
        return objService.claim(obj);
    }


    /**
     * 办理任务
     *
     * @param map 流程变量工具类，存放流程变量，任务id
     * @return
     */
    @RequestMapping("handle")
    @ResponseBody
    public SysResult handle(@RequestParam Map<String, Object> map) {
        return objService.handle(map);
    }

    /**
     * 分页条件查询待办
     *
     * @return
     */
    @RequestMapping("/getTaskBySelective")
    @ResponseBody
    public SysResult getTaskBySelective(ActTask obj) {
        SysResult taskResult = objService.getTaskBySelective(obj);
        if (taskResult.getMessage().equals("查询无记录")) return taskResult;
        return taskResult;
    }

    /**
     * 条件查询待办数量
     *
     * @return
     */
    @RequestMapping("/getTaskCountBySelective")
    @ResponseBody
    public SysResult getTaskCountBySelective(ActTask obj) {
        return objService.getTaskCountBySelective(obj);
    }

    /**
     * id查询待办
     *
     * @return
     */
    @RequestMapping("/getTaskById")
    @ResponseBody
    public SysResult getTaskById(String id) {
        return objService.getTaskById(id);
    }


    /**
     * 分页条件查询已办
     *
     * @return
     */
    @RequestMapping("/getHiTaskBySelective")
    @ResponseBody
    public SysResult getHiTaskBySelective(ActTask obj) {
        SysResult taskResult = objService.getHiTaskBySelective(obj);
        if (taskResult.getMessage().equals("查询无记录")) return taskResult;
        return taskResult;
    }


    /**
     * 条件查询已办数量
     *
     * @return
     */
    @RequestMapping("/getHiTaskCountBySelective")
    @ResponseBody
    public SysResult getHiTaskCountBySelective(ActTask obj) {
        return objService.getHiTaskCountBySelective(obj);
    }


    /**
     * id查询已办
     *
     * @return
     */
    @RequestMapping("/getHiTaskById")
    @ResponseBody
    public SysResult getHiTaskById(String id) {
        return objService.getHiTaskById(id);
    }

    /**
     * @return
     * @Author y_xiaopeng
     * @Description 流程转向
     * @Date 16:58 2020/4/29
     * @Param
     **/
    @PostMapping("/turnTransition")
    @ResponseBody
    public SysResult turnTransition(ActTask actTask) {
        return objService.turnTransition(actTask);
    }
}
