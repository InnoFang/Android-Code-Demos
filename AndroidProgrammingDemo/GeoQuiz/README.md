# GeoQuiz 
 **Android编程权威指南第二版 DEMO #1**
- - - 
  测试用户的地理知识。用户单击TRUE或FALSE按钮来回答屏幕上的问题，GeoQuiz可即时反馈答案正确与否
>第一个用户界面 (QuizActivity.java & activity_quiz.xml)

<img src="http://a3.qpic.cn/psb?/V13lwktv4Pl1jK/o1*wcqMNByx3BIlz17ahnvKBHl9l2CxUvLtZSEQglKI!/b/dLAAAAAAAAAA&bo=LALeAwAAAAADB9E!&rf=viewer_4" width = "300" height = "500" align=center />       

<img src="http://a3.qpic.cn/psb?/V13lwktv4Pl1jK/QJuXB29XFSLMhJiYL1hBlJjhYo2q4m*ExXhG2OO3Ohs!/b/dK0AAAAAAAAA&bo=AAXQAgAF0AIDACU!&rf=viewer_4" width = "500" height = "300" align = center/>
 
 * 添加了previous button，使用户可以查看前面的问题
 * 解决了由于旋转屏幕导致的问题重置问题 
 
>第二个用户界面，方便用户查看当前问题的答案 (CheatActivity.java & activity_cheat.xml)

<img src = "http://a3.qpic.cn/psb?/V13lwktv4Pl1jK/LFtLRRcwiYNn4nQCgjKYQjy*dOWmKgJUIehgUqNKiHg!/b/dP4AAAAAAAAA&bo=LALeAwAAAAADB9E!&rf=viewer_4" width = "300" height = "500" align = "center"/>

  * 解决挑战练习
    * 用户作弊后，可以旋转CheatActivity来清除作弊痕迹
    * 作弊返回后，用户可以旋转QuizActivity来清除mIsCheater变量值
    * 用户可以不断单击NEXT按钮，跳到偷看过答案的问题，从而使作弊记录丢失
    * 报告编译版本
