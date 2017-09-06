## spring security oauth2实现分离的 Authorization Server 和 Resource Server
Authorization Server 和 Resource Server 都会对token进行管理，但是各自的功能有所不同，分别体现在：[AuthorizationServerTokenServices](https://docs.spring.io/spring-security/oauth/apidocs/org/springframework/security/oauth2/provider/token/AuthorizationServerTokenServices.html)和[ResourceServerTokenServices ](https://docs.spring.io/spring-security/oauth/apidocs/org/springframework/security/oauth2/provider/token/ResourceServerTokenServices.html)   
AuthorizationServerTokenServices 提供如下功能：  
- 创建token
- 获取token
- 刷新token
ResourceServerTokenServices 提供如下功能：  
- 根据access_token获取授权信息
- 根据access_token获取token信息
可以了解两个接口的具体实现