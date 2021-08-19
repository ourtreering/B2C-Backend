package com.sillock.common.message;

import lombok.NoArgsConstructor;

/**
 * created by hyunwoo 21/06/23
 */
@NoArgsConstructor
public class ResponseMessage {
    public static final String EXAMPLE = "메시지 예시입니다.";

    // Default
    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";

    // Auth
    public static final String LOGIN_SUCCESS = "로그인에 성공했습니다.";
    public static final String SIGN_UP_SUCCESS = "회원가입에 성공했습니다.";

    // Member
    public static final String READ_MEMBER_PROFILER = "사용자 정보를 조회합니다.";

    // Sillog
    public static final String REGISTER_SILLOG = "실록 등록이 완료되었습니다.";
    public static final String READ_EVENT = "행사정보를 성공적으로 불러왔습니다.";
    public static final String READ_SILLOG_LIST = "실록 리스트를 조회합니다.";
    public static final String READ_SILLOG_TITLE_LIST = "실록 타이틀 리스트를 조회합니다.";


    // Tag
    public static final String READ_DEFAULT_TAG_INFO_LIST = "기본 태그 정보 리스트를 조회합니다.";
    public static final String READ_MEMBER_TAG_INFO_LIST = "사용자가 사용한 태그 정보 리스트를 조회합니다.";
}
