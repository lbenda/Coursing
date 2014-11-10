/*
 * Copyright 2014 Lukas Benda <lbenda at lbenda.cz>
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
package cz.lbenda.coursing.client.gui.action;

import cz.lbenda.coursing.dto.DTO;
import cz.lbenda.coursing.service.AbstractDTOService;
import org.openide.nodes.Node;

/** Add number line cookie
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public interface DTOAddCookie extends Node.Cookie {
  AbstractDTOService numberLineService();
  void showForm(DTO numberLine);
}
