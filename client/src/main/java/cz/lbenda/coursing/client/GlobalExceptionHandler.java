/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client;

import cz.lbenda.coursing.client.gui.LoginForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

/** Global handler for all exception which is thrown in client
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
// @ServiceProvider(service = Handler.class, supersedes = "org.netbeans.core.NbErrorManager")
public class GlobalExceptionHandler extends Handler implements Callable<JButton> {

  private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  private JButton reviewIssueButton;
  private NewFunctionActionListener newFunctionActionListener = new NewFunctionActionListener();

  @Override
  public void publish(LogRecord record) {
    boolean noCredential = false;
    Throwable tw = record.getThrown();
    while (tw != null) {
      LOG.trace("tw", tw);
      if (tw instanceof AuthenticationCredentialsNotFoundException) { noCredential = true; }
      tw = tw.getCause();
    }
    if (noCredential) {
      LOG.debug("noCredential");
      LoginForm.showLoginDialog();
      /*
      LoginDialog dialog = new LoginDialog(WindowManager.getDefault().getMainWindow(), true);
      dialog.setVisible(true);
              */
    } else if (record.getThrown() != null) {
      newFunctionActionListener.setLogRecord(record);
      // TODO show dialog
    }
  }

  @Override
  public void flush() {
  }

  @Override
  public void close() throws SecurityException {
  }

  @Override
  public JButton call() throws Exception {
    if (reviewIssueButton == null) {
      reviewIssueButton = new JButton("Review and Submit Issue");
      reviewIssueButton.addActionListener(newFunctionActionListener);
    }

    return reviewIssueButton;
  }

  private class NewFunctionActionListener implements ActionListener {

    private LogRecord logRecord;

    public NewFunctionActionListener() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      // Close our Uncaught Exception Dialog first.
      SwingUtilities.windowForComponent(reviewIssueButton).setVisible(false);
    }

    public void setLogRecord(LogRecord logRecord) {
      this.logRecord = logRecord;
    }
  }
}
