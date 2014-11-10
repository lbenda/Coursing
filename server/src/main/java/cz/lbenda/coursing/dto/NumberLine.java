/*
 * Copyright 2014 Lukas Benda <lbenda at lbenda.cz>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cz.lbenda.coursing.dto;

import cz.lbenda.coursing.common.NumberLineType;

/** Interface of entity which represent number line value
 * Created by Lukas Benda <lbenda @ lbenda.cz> on 6/21/14.
 */
public interface NumberLine extends DTO {
    NumberLineType getNumberLineType();

    String getName();
    void setName(String name);

    String getComment();
    void setComment(String comment);

    int getSort();
    void setSort(int sort);
}
