package com.sillock.common.message;

import lombok.NoArgsConstructor;

/**
 * created by hyunwoo 21/06/23
 */
@NoArgsConstructor
public class ResponseMessage {
    public static final String EXAMPLE = "메시지 예시입니다.";
    public static final String REGISTER_SILLOG = "실록 등록이 완료되었습니다.";
    public static final String READ_EVENT = "행사정보를 성공적으로 불러왔습니다.";
    public static final String READ_SILLOG_LIST = "실록 리스트를 조회합니다.";
    public static final String READ_SILLOG_TITLE_LIST = "실록 타이틀 리스트를 조회합니다.";
}
