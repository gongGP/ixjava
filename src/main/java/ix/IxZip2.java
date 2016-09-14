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

final class IxZip2<T1, T2, R> extends Ix<R> {

    final Iterable<T1> source1;

    final Iterable<T2> source2;

    final IxFunction2<? super T1, ? super T2, ? extends R> zipper;

    IxZip2(Iterable<T1> source1, Iterable<T2> source2, IxFunction2<? super T1, ? super T2, ? extends R> zipper) {
        this.source1 = source1;
        this.source2 = source2;
        this.zipper = zipper;
    }

    @Override
    public Iterator<R> iterator() {
        return new Zip2Iterator<T1, T2, R>(source1.iterator(), source2.iterator(), zipper);
    }

    static final class Zip2Iterator<T1, T2, R> extends IxBaseIterator<R> {

        final Iterator<T1> source1;

        final Iterator<T2> source2;

        final IxFunction2<? super T1, ? super T2, ? extends R> zipper;

        Zip2Iterator(Iterator<T1> source1, Iterator<T2> source2, IxFunction2<? super T1, ? super T2, ? extends R> zipper) {
            this.source1 = source1;
            this.source2 = source2;
            this.zipper = zipper;
        }

        @Override
        protected boolean moveNext() {
            if (!source1.hasNext()) {
                done = true;
                return false;
            }

            T1 t1 = source1.next();

            if (!source2.hasNext()) {
                done = true;
                return false;
            }

            T2 t2 = source2.next();

            value = zipper.apply(t1, t2);
            hasValue = true;
            return true;
        }

    }
}
