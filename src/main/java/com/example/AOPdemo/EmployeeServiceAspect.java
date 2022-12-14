package com.example.AOPdemo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmployeeServiceAspect {

    @Before(value = "execution(* com.example.AOPdemo.EmployeeService.*(..)) and args(name,empId)")
    public void beforeAdvice(JoinPoint joinPoint, String name, String empId) {
        System.out.println("Before method:" + joinPoint.getSignature());

        System.out.println("Creating Employee with name - " + name + " and id - " + empId);
    }

    @After(value = "execution(* com.example.AOPdemo.EmployeeService.*(..)) and args(name,empId)")
    public void afterAdvice(JoinPoint joinPoint, String name, String empId) {
        System.out.println("After method:" + joinPoint.getSignature());

        System.out.println("Successfully created Employee with name - " + name + " and id - " + empId);
    }

    @AfterReturning(value="execution(* com.example.AOPdemo.EmployeeService.*(..))",returning="employee")
    public void afterReturningAdvice(JoinPoint joinPoint, Employee employee)
    {
        System.out.println("After Returing method:"+joinPoint.getSignature());
        System.out.println(employee.getName() +"  "+ employee.getEmpId());
    }

    @Around(value= "execution(* com.example.AOPdemo.EmployeeService.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint jp) throws Throwable
    {
        Object[] list = jp.getArgs();
        list[0] = "New Emp Id";
        list[1] = "New Emp Name";
        Object result = null;
        System.out.println("The method aroundAdvice() before invokation of the method " + jp.getSignature().getName() + " method");
        try
        {
            result = jp.proceed(list);
        }
        finally
        {

        }
        System.out.println("The method aroundAdvice() after invokation of the method " + jp.getSignature().getName() + " method");
        return result;
    }

}
