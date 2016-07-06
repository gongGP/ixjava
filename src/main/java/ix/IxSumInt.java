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

final class IxSumInt extends IxSource<Integer, Integer> {

    public IxSumInt(Iterable<Integer> source) {
        super(source);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new SumIntIterator(source.iterator());
    }

    static final class SumIntIterator extends IxSourceIterator<Integer, Integer> {

        public SumIntIterator(Iterator<Integer> it) {
            super(it);
        }

        @Override
        protected boolean moveNext() {
            Iterator<Integer> it = this.it;
            if (!it.hasNext()) {
                done = true;
                return false;
            }
            int sum = it.next();

            while (it.hasNext()) {
                sum += it.next();
            }
            
            value = sum;
            hasValue = true;
            done = true;
            return true;
        }
        
    }
}
