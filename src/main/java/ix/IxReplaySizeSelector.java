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

import java.util.Iterator;

import rx.functions.Func1;

final class IxReplaySizeSelector<T, R> extends IxSource<T, R> {

    final int maxSize;
    
    final Func1<? super Ix<T>, ? extends Iterable<? extends R>> selector;
    
    public IxReplaySizeSelector(Iterable<T> source, int maxSize, 
            Func1<? super Ix<T>, ? extends Iterable<? extends R>> selector) {
        super(source);
        this.maxSize = maxSize;
        this.selector = selector;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Iterator<R> iterator() {
        
        return (Iterator<R>)selector.call(new IxReplaySize<T>(source, maxSize)).iterator();
    }
}
