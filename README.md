# new-youpass-backend

# Account

1. 注册 SignupController.Post
2. 登陆 LoginController.Post
3. todo list /放前端处理
4. User infomation UserInfomationController/all
5. User image UserImageController.Get/Post



# Question

1. 通过题目获得一个题目没有批改过的学生名单 GetStudofQuesController.Post //修改成Get
2. 通过考生信息获得考试所有题目信息 TakeexamController.Get
4. 上传题目到题库 UploadQuestionController.Post



# Exam

1. 发布考试 CreateExamController.Post
2. 通过课程id获得考试信息 GetCourseExam.Get
3. 设置考试session TakeexamController.SetSession
4. 删除session TakeexamController.Delete
5. 查看课程的所有考试 ExamController.GetbyCourseId
6. 学生查看所有考试信息 ExamController.Get
6. 学生上传答案 TakeexamController.PostAnswer



# Score

1. 计算试卷总分 ExamController.CalStuScore
2. 查找某门考试所有学生的分数 CourseController.GradeGet
3. 自动批改 CorrectingChoiceController.Post   **//未测试**
4. 手动批改 CorrectingChoiceController.ManualCorrectingPost  **//未测试**
5. 通过student_id &course_id 获得学生该门课所有考试的成绩 **//未测试** ExamController.GetStuScore

# Student

1. 通过课程id获得学生信息 CourseController.StuGet
2. 删除课程的学生CorrectingChoiceController.DeleteStu



# Course

1. 删除课程 CorrectingChoiceController.Delete
2. 三种方式搜索课程  GetCourseBy/Id/Title/Tname
3. 通过用户id获得课程 CourseController.Get()
4. 老师创建课程 CourseController.Post()
5. 加入课程 JoinCourseController.Post



# Notice

1. 查看消息 NoticeCheckController.Get







