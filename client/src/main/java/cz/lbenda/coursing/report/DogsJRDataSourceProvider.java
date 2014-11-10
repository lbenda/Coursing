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
package cz.lbenda.coursing.report;

import cz.lbenda.coursing.client.ClientServiceLocator;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRDataSourceProvider;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JasperReport;

/**
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public class DogsJRDataSourceProvider implements JRDataSourceProvider {

  @Override
  public boolean supportsGetFieldsOperation() { return true; }

  @Override
  public JRField[] getFields(JasperReport jr) throws JRException, UnsupportedOperationException {
    return DogsJRDataSource.FIELDS.toArray(new JRField[DogsJRDataSource.FIELDS.size()]);
  }

  @Override
  public JRDataSource create(JasperReport jr) throws JRException {
    return ClientServiceLocator.getInstance().getBean(DogsJRDataSource.class);
  }

  @Override
  public void dispose(JRDataSource jrds) throws JRException {
    if (jrds instanceof DogsJRDataSource) { ((DogsJRDataSource) jrds).dispose(); }
  }
}
