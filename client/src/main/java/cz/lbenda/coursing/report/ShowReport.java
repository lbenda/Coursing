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

import cz.lbenda.coursing.service.DogService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
@Service("ShowReport")
public class ShowReport {

  private Logger LOG = LoggerFactory.getLogger(ShowReport.class);

  @Autowired
  private DogService dogService;

  public void ShowReport() {}

  public void printDogs() {
    try {
      // InputStream rs = getClass().getResourceAsStream("/cz/lbenda/coursing/report/dogs.jrxml");
      FileInputStream rs = null;
      try {
        rs = new FileInputStream("/home/benzin/work/java/coursing/client/src/main/resources/cz/lbenda/coursing/report/dogs.jrxml");
      } catch (FileNotFoundException ex) {
        LOG.error("Problem with found", ex);
      }
      // JasperReport jr = JasperCompileManager.compileReport(ShowReport.class.getResourceAsStream());
      // JasperReport jr = JasperCompileManager.compileReport(ShowReport.class.getResourceAsStream("/cz/lbenda/coursing/report/dogs.jasper"));
      JasperReport jr = JasperCompileManager.compileReport(rs);//getClass().getResourceAsStream("/cz/lbenda/coursing/report/dogs.jrxml"));

      JasperPrint jp = JasperFillManager.fillReport(jr, new HashMap<String, Object>(),
              DogsJRDataSource.getDataSource());
      JasperExportManager.exportReportToPdfFile(jp, "report1.pdf");
      JasperViewer.viewReport(jp, false);
    } catch (JRException ex) {
      LOG.error("Problem with generate jasper report.", ex);
    }
  }

}
