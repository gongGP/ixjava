/*
 * Copyright 2011-2016 David Karnok
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ix;

import rx.functions.Func1;

enum FunctionHelper {
    ;
    
    enum Identity implements Func1<Object, Object> {
        INSTANCE
        ;
        
        @SuppressWarnings("unchecked")
        public static <T> Func1<T, T> instance() {
            return (Func1<T, T>)INSTANCE;
        }
        
        @Override
        public Object call(Object t) {
            return t;
        }
    }
    
    enum NumberToLong implements Func1<Number, Long> {
        INSTANCE;
        
        @Override
        public Long call(Number t1) {
            return t1.longValue();
        }
    }
}
