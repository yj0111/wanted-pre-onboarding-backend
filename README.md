# wanted-pre-onboarding-backend
프리온보딩 백엔드 인턴쉽 사전과제

<br>

## Entity Diagram
<img width="608" alt="스크린샷 2024-08-08 오전 2 01 58" src="https://github.com/user-attachments/assets/ccd6e607-998e-422b-ad63-94b2cd7b6741">

- apply : member = N : 1
- apply : posting= N : 1
- posting : company = N : 1

<br>
<br>

## 1️⃣ 채용공고 등록
### 요구사항
채용공고를 등록합니다.

`POST` /posting/regist 

#### Request
```
{
  "companyId": 1,
  "postingNation": "대한민국",
  "postingRegion": "서울",
  "postingPosition": "백엔드 개발자",
  "postingBonus": "100000",
  "postingSkills": "Java",
  "postingDetail": "네이버에서 개발자를 모집합니다...."
}

```

#### Response
```
{
    "statusCode": 200,
    "messages": "성공 :)",
    "developerMessage": "성공하였습니다.",
    "timestamp": "2024-08-07T23:14:47.515206"
}
```

## 2️⃣ 채용공고 수정
### 요구사항
회사는 아래 데이터와 같이 채용공고를 수정합니다. (회사 id 이외 모두 수정 가능합니다.)

`PATCH` /posting/update/{companyId}/{postingId} 

#### Request
```
{
  "postingNation": "대한민국",
  "postingRegion": "대전",
  "postingPosition": "프론트엔드 개발자",
  "postingBonus": "500000",
  "postingSkills": "React",
  "postingDetail": "네이버에서 프론트 개발자를 모집합니다...."
}
```

#### Response
```
{
    "statusCode": 200,
    "messages": "성공 :)",
    "developerMessage": "성공하였습니다.",
    "timestamp": "2024-08-07T23:14:47.515206"
}
```

## 3️⃣ 채용공고 삭제
### 요구사항
채용공고를 삭제합니다.

`DELETE`/posting/{postingId}

#### Response
```
{
    "statusCode": 200,
    "messages": "성공 :)",
    "developerMessage": "성공하였습니다.",
    "timestamp": "2024-08-07T23:14:47.515206"
}
```

## 4️⃣-1️⃣ 채용공고목록 조회
### 요구사항
전체 채용공고를 리스트로 반환합니다

`GET`/posting

#### Response
```
{
    "statusCode": 200,
    "messages": "성공 :)",
    "developerMessage": "성공하였습니다.",
    "timestamp": "2024-08-08T01:51:58.550727",
    "data": [
        {
            "postingId": 1,
            "companyName": "원티드",
            "postingNation": "대한민국",
            "postingRegion": "서울",
            "postingPosition": "백엔드 주니어 개발자",
            "postingBonus": "500000",
            "postingSkills": "Django"
        },
        {
            "postingId": 2,
            "companyName": "원티드",
            "postingNation": "대한민국",
            "postingRegion": "부산",
            "postingPosition": "프론트엔드 개발자",
            "postingBonus": "500000",
            "postingSkills": "javascript"
        },
        {
            "postingId": 3,
            "companyName": "원티드",
            "postingNation": "대한민국",
            "postingRegion": "서울",
            "postingPosition": "백엔드 주니어 개발자",
            "postingBonus": "500000",
            "postingSkills": "Python"
        },
        ...
    ]
}
```


## 4️⃣-2️⃣ 채용공고 검색기능 (선택사항 및 가산점요소)
### 요구사항
키워드를 포함하는 채용공고를 리스트로 반환합니다.

`GET`/posting/search?keyword={keyword}

#### Response
```

{
    "statusCode": 200,
    "messages": "성공 :)",
    "developerMessage": "성공하였습니다.",
    "timestamp": "2024-08-08T01:51:58.550727",
    "data": [
        {
            "postingId": 1,
            "companyName": "원티드",
            "postingNation": "대한민국",
            "postingRegion": "서울",
            "postingPosition": "백엔드 주니어 개발자",
            "postingBonus": "500000",
            "postingSkills": "Django"
        },
        {
            "postingId": 6,
            "companyName": "투티드",
            "postingNation": "대한민국",
            "postingRegion": "서울",
            "postingPosition": "백엔드 주니어 개발자",
            "postingBonus": "500000",
            "postingSkills": "Django"
        }
    ]
}
```

- NativeQuery 사용
- 검색어가 회사 이름, 포지션, 스킬에 포함된 내용이 있으면 공고를 조회하는 검색 기능을 구현하기 위해 nativeQuery를 이용하였습니다.
```
    @Query(value = "SELECT p.* FROM posting p " +
            "JOIN company c ON p.company_id = c.company_id " +
            "WHERE c.company_name LIKE %:keyword% OR " +
            "p.posting_position LIKE %:keyword% OR " +
            "p.posting_skills LIKE %:keyword%", nativeQuery = true)
    List<Posting> searchPostings(@Param("keyword") String keyword);
```


## 5️⃣ 채용공고 상세조회 
### 요구사항
- 사용자는 채용상세 페이지를 아래와 같이 확인할 수 있습니다.
- “채용내용”이 추가적으로 담겨있음.
- 해당 회사가 올린 다른 채용공고 가 추가적으로 포함됩니다.
  
`GET`/posting/{postingId}

#### Response
```
{
    "statusCode": 200,
    "messages": "성공 :)",
    "developerMessage": "성공하였습니다.",
    "timestamp": "2024-08-08T01:51:58.550727",
    "data": {
        "postingId": 1,
        "companyName": "원티드",
        "postingNation": "대한민국",
        "postingRegion": "서울",
        "postingPosition": "백엔드 주니어 개발자",
        "postingBonus": "500000",
        "postingSkills": "Django",
        "postingDetail": "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은..",
        "otherPostingList": [
            2,
            3,
            4
        ]
    }
}
```
## 6️⃣ 사용자 채용공고 지원 (선택사항 및 가산점요소)
### 요구사항
- 사용자는 채용공고에 아래와 같이 지원합니다. (가점 요소이며, 필수 구현 요소가 아님)
- 사용자는 1회만 지원 가능합니다.

`POST`/apply/post

#### Request
```
{
  "memberId": 2,
  "postingId": 5
}
```

#### Response
##### 지원 성공
```
{
    "statusCode": 200,
    "messages": "성공 :)",
    "developerMessage": "성공하였습니다.",
    "timestamp": "2024-08-08T01:51:58.550727"
}
```

##### 지원 실패
```
{
    "statusCode": 433,
    "messages": "에러발생 :(",
    "developerMessage": "이미 지원한 공고입니다.",
    "timestamp": "2024-08-08T01:54:16.628853"
}
```


## Exception Handling

예외 코드를 관리하기 위해 `ExceptionCode` enum을 사용하였습니다.
발생할 수 있는 주요 예외 상황을 코드와 메시지로 정의하여, 일관된 오류 응답을 제공하였습니다.

```
@Getter
@AllArgsConstructor
public enum ExceptionCode {
    NOT_EXIST_COMPANY_EXCEPTION(430,"회사가 존재하지 않습니다."),
    NOT_EXIST_POSTING_EXCEPTION(431,"공고가 존재하지 않습니다."),
    NOT_EXIST_MEMBER_EXCEPTION(432,"회원이 존재하지 않습니다."),
    ALREADY_APPLY_EXCEPTION(433,"이미 지원한 공고입니다."),
    SERVER_EXCEPTION(500, "서버에서 예측하지 못한 에러가 발생했습니다.");

    private final int errorCode;
    private final String errorMessage;
}
```

## Commit Convention

| **convention** | **설명**                  |
|:--------------:|:------------------------|
|      Feat      | 새로운 기능을 추가한 경우   |
|      Test      | 테스트 코드 작성  |
|     Refactor   | 효율적인 코드를 위해 코드 수정 및 리팩토링을 진행 |
|      Fix       | 오류를 수정 할 경우             |
|      Docs      | 문서 작성/수정 할 경우           |


