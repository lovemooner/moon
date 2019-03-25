/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package love.moon.config;



import love.moon.annotation.Annotation100;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * HmilyFeignHandler.
 *
 * @author xiaoyu
 */
public class InvocationHandler100 implements InvocationHandler {

    private InvocationHandler delegate;

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }
        final Annotation100 annotation = method.getAnnotation(Annotation100.class);
        if (Objects.isNull(annotation)) {
            return this.delegate.invoke(proxy, method, args);
        }
        try {
            System.out.println("before invoke");
            final Object invoke = delegate.invoke(proxy, method, args);
            System.out.println("after invoke");
            return invoke;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw throwable;
        }

    }


    void setDelegate(InvocationHandler delegate) {
        this.delegate = delegate;
    }

}
