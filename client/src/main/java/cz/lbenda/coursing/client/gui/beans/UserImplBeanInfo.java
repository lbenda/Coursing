/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.beans;

import cz.lbenda.coursing.server.user.UserImpl;
import java.awt.Image;
import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.SimpleBeanInfo;
import javax.swing.ImageIcon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Bean info for bean UserImpl
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
public class UserImplBeanInfo extends SimpleBeanInfo {

  private static final Logger LOG = LoggerFactory.getLogger(UserImplBeanInfo.class);

  @Override
  public BeanDescriptor getBeanDescriptor() {
    return new BeanDescriptor(UserImpl.class, UserCustomizer.class);
  }

  private static Image smallColor;
  private static Image largeColor;
  private static Image smallMono;
  private static Image largeMono;

  static {
    try {
      smallColor = createImageIcon("/cz/lbenda/coursing/client/icon/user.png", "User").getImage();
      largeColor = createImageIcon("/cz/lbenda/coursing/client/icon/user32.png", "User").getImage();
      smallMono = createImageIcon("/cz/lbenda/coursing/client/icon/user_m.png", "User").getImage();
      largeMono = createImageIcon("/cz/lbenda/coursing/client/icon/user_m32.png", "User").getImage();
    } catch (Exception e) {
      LOG.error("Problem with define image icons", e);
    }
  }

  @Override
  public Image getIcon(int iconKind) {
    switch (iconKind) {
      case BeanInfo.ICON_COLOR_16x16 : return smallColor;
      case BeanInfo.ICON_COLOR_32x32 : return largeColor;
      case BeanInfo.ICON_MONO_16x16 : return smallMono;
      case BeanInfo.ICON_MONO_32x32 : return largeMono;
      default : return smallColor;
    }
  }

  /** Returns an ImageIcon, or null if the path was invalid. */
  private static ImageIcon createImageIcon(String path, String description) {
    java.net.URL imgURL = UserImplBeanInfo.class.getResource(path);
    if (imgURL != null) {
      return new ImageIcon(imgURL, description);
    } else {
      LOG.error("Couldn't find file: " + path);
      return null;
    }
  }
}
