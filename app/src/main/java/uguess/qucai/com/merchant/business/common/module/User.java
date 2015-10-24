package uguess.qucai.com.merchant.business.common.module;

import android.location.Location;
import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by NO1 on 2015/1/18.
 */
public class User implements Serializable {
    /**
     * 用户帐户
     */
    private String userName;
    /**
     * 用户密码
     */
    private String passWord;
    /**
     * 用户token
     */
    private String token;
    /**
     * 用户头像
     */
    private String portraitURL;
    /**
     * 用户手机号
     */
    private String cellNum;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 验证码
     */
    private String verifyCode;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 用户生日
     */
    private String birthday;
    /**
     * 性别
     */
    private int gender;
    /**
     * 未读信息数
     */
    private int unreadMsgCount;

    private String header;

    private String userImId;

    private String friendId;

    private int confirm;
    /**
     * 用户等级
     */
    private int userLevel;

    private String createTime;
    /**
     * 上传图片列表的index
     */
    private int index;

    private int selectIndex;

    //好友来源
    private String source;

    private boolean inBlackList;



    /**
     * 最后登录时间
     */
    private String lastLoginTime;

    /**
     * 站点名称
     */
    private String stationName;

    /**
     * 用户位置信息
     */
    private Location location;


    /* 有效福利数 */
    private int validBenefitCount;

    /* 联系人数 */
    private int friendCount;

    /**
     * 上传头像图片令牌
     */
    private String avatarsToken;

    /**
     * 上传普通图片令牌
     */
    private String imagesToken;
    /**
     * 设备deviceToken
     */
    private String deviceId;
    /**
     * 是否为初次登陆,0否，1是
     */
    private boolean isFirstLogin;
    /**
     * 若为第三方登陆则此字段不为空
     */
    private String openId;
    /**
     * 用户的登录模式 -1,正常登录，0：微信登录，1：微博登录，2：QQ登录
     */
    private int loginMode = -1;
    /**
     * 用户登录后返回的加密串，用于自动登录
     */
    private String encrypt;
    /**
     * 星座
     */
    private String constellation;
    /**
     * 红包余额(单位:元)
     */
    private double bonusBalance;
    /**
     * 猜豆余额
     */
    private long scoreBalance;
    /**
     * 经验值
     */
    private long empiricValue;
    /**
     * 钻石
     */
    private long diamondBalance;
    /**
     * 卡券余额
     */
    private long couponBalance;
    /**
     * 资源余额
     */
    private long resourceBalance;
    /**
     * 来源类型
     */
    private String SourceType;

    private boolean operationFlag;

    private String applyId;

    private String userAnswer;

    private int isAnswerRight;
    /**
     * 最新竞猜ID
     */
    private String quizId;
    /**
     * 最新竞猜内容
     */
    private String content;
    /**
     * 关注他人数量
     */
    private int concernNum;
    /**
     * 粉丝数量
     */
    private int fansNum;
    /**
     * 勋章数量
     */
    private int medalNum;
    /**
     * 发布竞猜数量
     */
    private int publishGuessNum;
    /**
     * 参与竞猜数量
     */
    private int joinGuessNum;
    /**
     * 用户级别 1普通用户；2VIP；3企业用户
     */
    private int grade;
    /**
     * 邀请码
     */
    private String inviteCode;
    /**
     * 上传文档令牌
     */
    private String documentToken;
    /**
     * 用户升级状态 -1申请认证；0等待审核；1审核中；2审核通过；3审核不通过；
     */
    private int upgradeStatus;

    /**
     * 未读好友验证消息
     */
    private int unreadFriendCheckNum;
    /**
     * 用户参与竞猜时间
     */
    private Long joinTime;
    /**
     * 用户是否赢得竞猜
     */
    private int isWin;
    /**
     * 竞猜答案类型
     */
    private int guessAnswerType;

    /**
     * 支付密码已设置
     */
    private boolean payPwdSetted;
    /**
     * 用户选择下标
     */
    private String userAnswerIndex;
    /**
     * 用户答案类型
     */
    private int answerType;
    /**
     * 答案文件
     */
    private String fileUrl;
    /**
     * 缩略图
     */
    private String thumbnailUrl;
    /**
     * 持续时间
     */
    private int duration;
    /**
     * 用户的备注姓名
     */
    private String remarkName;
    /**
     * 验证信息
     */
    private String verifyInfo;
    /**
     * 第三方登录是否为新用户
     * 若认证失败则无此
     * 0:否 1:是
     */
    private int isNewUser = -1;

    public int getIsNewUser() {
        return isNewUser;
    }

    public void setIsNewUser(int isNewUser) {
        this.isNewUser = isNewUser;
    }

    public String getShowName() {
        if (TextUtils.isEmpty(remarkName)) {
            return nickName;
        } else {
            return remarkName;
        }
    }

    public String getVerifyInfo() {
        return verifyInfo;
    }

    public void setVerifyInfo(String verifyInfo) {
        this.verifyInfo = verifyInfo;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getUserAnswerIndex() {
        return userAnswerIndex;
    }

    public void setUserAnswerIndex(String userAnswerIndex) {
        this.userAnswerIndex = userAnswerIndex;
    }

    public int getAnswerType() {
        return answerType;
    }

    public void setAnswerType(int answerType) {
        this.answerType = answerType;
    }

    public Long getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Long joinTime) {
        this.joinTime = joinTime;
    }

    public int getIsWin() {
        return isWin;
    }

    public void setIsWin(int isWin) {
        this.isWin = isWin;
    }

    public long getResourceBalance() {
        return resourceBalance;
    }

    public void setResourceBalance(long resourceBalance) {
        this.resourceBalance = resourceBalance;
    }

    public long getCouponBalance() {
        return couponBalance;
    }

    public void setCouponBalance(long couponBalance) {
        this.couponBalance = couponBalance;
    }

    public int getUnreadFriendCheckNum() {
        return unreadFriendCheckNum;
    }

    public void setUnreadFriendCheckNum(int unreadFriendCheckNum) {
        this.unreadFriendCheckNum = unreadFriendCheckNum;
    }

    public int getUpgradeStatus() {
        return upgradeStatus;
    }

    public void setUpgradeStatus(int upgradeStatus) {
        this.upgradeStatus = upgradeStatus;
    }

    public String getDocumentToken() {
        return documentToken;
    }

    public void setDocumentToken(String documentToken) {
        this.documentToken = documentToken;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getJoinGuessNum() {
        return joinGuessNum;
    }

    public void setJoinGuessNum(int joinGuessNum) {
        this.joinGuessNum = joinGuessNum;
    }

    public int getPublishGuessNum() {
        return publishGuessNum;
    }

    public void setPublishGuessNum(int publishGuessNum) {
        this.publishGuessNum = publishGuessNum;
    }

    public int getMedalNum() {
        return medalNum;
    }

    public void setMedalNum(int medalNum) {
        this.medalNum = medalNum;
    }

    public int getFansNum() {
        return fansNum;
    }

    public void setFansNum(int fansNum) {
        this.fansNum = fansNum;
    }

    public int getConcernNum() {
        return concernNum;
    }

    public void setConcernNum(int concernNum) {
        this.concernNum = concernNum;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getDiamondBalance() {
        return diamondBalance;
    }

    public void setDiamondBalance(long diamondBalance) {
        this.diamondBalance = diamondBalance;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public int getIsAnswerRight() {
        return isAnswerRight;
    }

    public void setIsAnswerRight(int isAnswerRight) {
        this.isAnswerRight = isAnswerRight;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public boolean isOperationFlag() {
        return operationFlag;
    }

    public void setOperationFlag(boolean operationFlag) {
        this.operationFlag = operationFlag;
    }

    public String getSourceType() {
        return SourceType;
    }

    public void setSourceType(String sourceType) {
        SourceType = sourceType;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public int getLoginMode() {
        return loginMode;
    }

    public void setLoginMode(int loginMode) {
        this.loginMode = loginMode;
    }

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }

    public int getSelectIndex() {
        return selectIndex;
    }

    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public boolean isFirstLogin() {
        return isFirstLogin;
    }

    public void setIsFirstLogin(boolean isFirstLogin) {
        this.isFirstLogin = isFirstLogin;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getGuessAnswerType() {
        return guessAnswerType;
    }

    public void setGuessAnswerType(int guessAnswerType) {
        this.guessAnswerType = guessAnswerType;
    }

    public User(){

    }

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPortraitURL() {
        return portraitURL;
    }

    public void setPortraitURL(String portraitURL) {
        this.portraitURL = portraitURL;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getCellNum() {
        return cellNum;
    }

    public void setCellNum(String cellNum) {
        this.cellNum = cellNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }



    public Location getLocation() {
        return location;
    }


    public int getUnreadMsgCount() {
        return unreadMsgCount;
    }

    public void setUnreadMsgCount(int unreadMsgCount) {
        this.unreadMsgCount = unreadMsgCount;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getUserImId() {
        return userImId;
    }

    public void setUserImId(String userImId) {
        this.userImId = userImId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isInBlackList() {
        return inBlackList;
    }

    public void setInBlackList(boolean inBlackList) {
        this.inBlackList = inBlackList;
    }

    public int getFriendCount() {
        return friendCount;
    }

    public void setFriendCount(int friendCount) {
        this.friendCount = friendCount;
    }

    public int getValidBenefitCount() {
        return validBenefitCount;
    }

    public void setValidBenefitCount(int validBenefitCount) {
        this.validBenefitCount = validBenefitCount;
    }

    public String getAvatarsToken() {
        return avatarsToken;
    }

    public void setAvatarsToken(String avatarsToken) {
        this.avatarsToken = avatarsToken;
    }

    public String getImagesToken() {
        return imagesToken;
    }

    public void setImagesToken(String imagesToken) {
        this.imagesToken = imagesToken;
    }

    public double getBonusBalance() {
        return bonusBalance;
    }

    public void setBonusBalance(double bonusBalance) {
        this.bonusBalance = bonusBalance;
    }

    public long getScoreBalance() {
        return scoreBalance;
    }

    public void setScoreBalance(long scoreBalance) {
        this.scoreBalance = scoreBalance;
    }

    public long getEmpiricValue() {
        return empiricValue;
    }

    public void setEmpiricValue(long empiricValue) {
        this.empiricValue = empiricValue;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public int getConfirm() {
        return confirm;
    }

    public void setConfirm(int confirm) {
        this.confirm = confirm;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public boolean isPayPwdSetted() {
        return payPwdSetted;
    }

    public void setPayPwdSetted(boolean payPwdSetted) {
        this.payPwdSetted = payPwdSetted;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", token='" + token + '\'' +
                ", portraitURL='" + portraitURL + '\'' +
                ", cellNum='" + cellNum + '\'' +
                ", userId='" + userId + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                ", nickName='" + nickName + '\'' +
                ", birthday='" + birthday + '\'' +
                ", gender=" + gender +
                ", unreadMsgCount=" + unreadMsgCount +
                ", header='" + header + '\'' +
                ", userImId='" + userImId + '\'' +
                ", friendId='" + friendId + '\'' +
                ", confirm=" + confirm +
                ", userLevel=" + userLevel +
                ", createTime='" + createTime + '\'' +
                ", index=" + index +
                ", selectIndex=" + selectIndex +
                ", source='" + source + '\'' +
                ", inBlackList=" + inBlackList +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                ", stationName='" + stationName + '\'' +
                ", location=" + location +
                ", validBenefitCount=" + validBenefitCount +
                ", friendCount=" + friendCount +
                ", avatarsToken='" + avatarsToken + '\'' +
                ", imagesToken='" + imagesToken + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", isFirstLogin=" + isFirstLogin +
                ", openId='" + openId + '\'' +
                ", loginMode=" + loginMode +
                ", encrypt='" + encrypt + '\'' +
                ", constellation='" + constellation + '\'' +
                ", bonusBalance=" + bonusBalance +
                ", scoreBalance=" + scoreBalance +
                ", empiricValue=" + empiricValue +
                ", diamondBalance=" + diamondBalance +
                ", couponBalance=" + couponBalance +
                ", resourceBalance=" + resourceBalance +
                ", SourceType='" + SourceType + '\'' +
                ", operationFlag=" + operationFlag +
                ", applyId='" + applyId + '\'' +
                ", userAnswer='" + userAnswer + '\'' +
                ", isAnswerRight=" + isAnswerRight +
                ", quizId='" + quizId + '\'' +
                ", content='" + content + '\'' +
                ", concernNum=" + concernNum +
                ", fansNum=" + fansNum +
                ", medalNum=" + medalNum +
                ", publishGuessNum=" + publishGuessNum +
                ", joinGuessNum=" + joinGuessNum +
                ", grade=" + grade +
                ", inviteCode='" + inviteCode + '\'' +
                ", documentToken='" + documentToken + '\'' +
                ", upgradeStatus=" + upgradeStatus +
                ", unreadFriendCheckNum=" + unreadFriendCheckNum +
                '}';
    }
}
