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

final class IxRange extends Ix<Integer> {

    final int start;
    
    final int end;
    
    public IxRange(int start, int count) {
        this.start = start;
        this.end = start + count;
    }
    
    @Override
    public Iterator<Integer> iterator() {
        return new RangeIterator(start, end);
    }
    
    static final class RangeIterator implements Iterator<Integer> {
        
        final int end;
        
        int index;
        
        public RangeIterator(int start, int end) {
            this.index = start;
            this.end = end;
        }

        @Override
        public boolean hasNext() {
            return index != end;
        }

        @Override
        public Integer next() {
            int i = index;
            if (i != end) {
                index = i + 1;
                return i;
            }
            return noelements();
        }
        
        @Override
        public void remove() {
            unsupported();
        }
    }

}