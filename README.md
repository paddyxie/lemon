# Lemon

A project provide some common components implementations.

* Simple AOP

### Simple AOP
AOP Alliance API implementation with Java Dynamic Proxy. Use MethodInterceptor to enhance method
invocation.
```
Executor executor = SimpleAop.newProxy(instance, interceptors);
```

