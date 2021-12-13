package com.youpass.controller;

import com.youpass.model.NoticeInfo;
import com.youpass.pojo.pk.TeacherId;
import com.youpass.service.NoticeService;
import com.youpass.util.ReturnType.Result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/notice")
public class NoticeController {
    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    /**
     * 获取一个学生所有的通知notice
     * @param id
     * @return
     * {
     *     "code": 100,
     *     "msg": "成功",
     *     "data": [
     *         {
     *             "noticeId": 2,
     *             "courseId": 1000,
     *             "teacherId": 10500,
     *             "content": "开课了111",
     *             "time": null,
     *             "courseTitle": "c1",
     *             "teacherName": "danny"
     *         },
     *         {
     *             "noticeId": 1,
     *             "courseId": 1000,
     *             "teacherId": 10500,
     *             "content": "开课了",
     *             "time": null,
     *             "courseTitle": "c1",
     *             "teacherName": "danny"
     *         }
     *     ]
     * }
     */
    @GetMapping(path = "getNotice")
    public Result<Object> GetNotice(@RequestAttribute(name = "id") Long id){
        return this.noticeService.getNotice(id);
    }

    /**
     * 发布新的通知
     * @param id            通过session获得的teacher id
     * @param noticeInfo    courseId    课程号
     *                      content     内容
     *                      time        创建时间
     * @return
     * {
     *     "code": 100,
     *     "msg": "成功",
     *     "data": null
     * }
     */
    @PutMapping(path = "generateNotice")
    public Result<Object> GenerateNotice(@RequestAttribute(name = "id") Long id, @RequestBody NoticeInfo noticeInfo){
        noticeInfo.setTeacherId(id);
        return noticeService.generateNotice(noticeInfo);
    }
}
