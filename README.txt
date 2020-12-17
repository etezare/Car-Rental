(function ($) {
    $.fn.serializeFormJSON = function () {

        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
})(jQuery)

$(document).ready(function (){
    $("#sub").click(function (){
        let data=JSON.stringify($("#courseForm").serializeFormJSON());
        $.ajax({
            type:"POST",
            url:"http://localhost:8080/addCourse",
            data:data,
            dataType:"json",
            contentType:"json/application",
            success:function (data){
                $("#courseForm")[0].reset();
                $("#result").empty();
                $("#result").append('<H3 align="center">okkkkkk</H3><p></p>').show()
            },
            error:function (XMLHttpRequest){
                $("#result").empty();
                if(XMLHttpRequest.responseJSON.errorType=="ValidationError"){
                    let errorList=XMLHttpRequest.responseJSON.fieldErrors;
                    let msg='<H3 align="center">Error!!</H3>'
                        msg+='<p>'
                    $.each(errorList,function (i,error){
                        msg+=error.message+'<br>';
                    })
                    msg+='</p>';
                    $("#result").append(msg).show()
                }
            }
        })
    })
})










<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form id="inputform" th:method="post" action="/save" th:object="${student}">
    <fieldset>
        <input th:value="${student.id}" th:for="id" th:id="id" hidden>
        <p>
            <label th:for="firstName">FirstName</label>
            <input th:id="firstName" th:name="firstName" th:value="${student.firstName}" th:type="text">
            <span th:if="${#fields.hasErrors('firstName')}" th:errors="${student.firstName}" style="color: red"></span>
        </p>
        <p>
            <label th:for="studentNumber">studentNumber:</label>
            <input th:id="studentNumber" th:type="text" th:value="${student.studentNumber}" id="studentNumber" th:name="studentNumber">
            <span th:if="${#fields.hasErrors('studentNumber')}" th:errors="${student.studentNumber}" style="color:red;"></span>
        </p>
        <p>
            <label th:for="lastName">lastName:</label>
            <input th:id="lastName" th:type="text" th:value="${student.lastName}" id="lastName" th:name="lastName">
            <span th:if="${#fields.hasErrors('lastName')}" th:errors="${student.lastName}" style="color:red;"></span>
        </p>
        <p>
            <label th:for="street">street:</label>
            <input th:type="text"  th:field="${student.address.street}" id="street" th:name="street">
            <span th:if="${#fields.hasErrors('address.street')}" th:errors="${student.address.street}" style="color:red;"></span>
        </p>
        <select th:field="${student.courseList}" multiple="multiple" size="3">
            <option th:each="course:${courses}"
            th:value="${{course}}"
            th:text="${course.name}">
            </option>
        </select>
    </fieldset>
    <button type="submit">save</button>
</form>

</body>
</html>




package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class RestErrorHandler {
//
//	@Autowired
//	private MessageSource messageSource;



	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ValidationErrorDTO processValidationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		return processFieldErrors(fieldErrors);
	}

	private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
	ValidationErrorDTO dto = new ValidationErrorDTO("ValidationError");

		for (FieldError fieldError : fieldErrors) {
			dto.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return dto;
	}



}



spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.datasource.platform=h2
spring.jpa.show-sql=true
spring.datasource.url=jdbc:h2:mem:waa
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
server.error.path=/error


