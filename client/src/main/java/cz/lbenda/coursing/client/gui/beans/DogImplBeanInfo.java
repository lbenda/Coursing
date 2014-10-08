/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.lbenda.coursing.client.gui.beans;

import cz.lbenda.coursing.server.dto.DogImpl;
import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Bean information for DTO object Dog
 * @author Lukas Benda <lbenda at lbenda.cz>
 */
@Messages({
  "PDN_DTO_id=Database ID",
  "PDN_DTO_modifier=Last user",
  "PDN_DTO_modified=Last change",
  "DN_Dog=Dog",
  "PDN_Dog_name=Name",
  "PDN_Dog_breed=Breed",
  "PDN_Dog_genderType=Gender",
  "PDN_Dog_licenceNumber=License number",
  "PDN_Dog_birthdate=Birthdate",
  "PDN_Dog_comment=Comment",

  "PSD_DTO_id=Identifier of entity in database",
  "PSD_DTO_modifier=User which changed entity at last",
  "PSD_DTO_modified=Date time when was the entity last at last",
  "SD_Dog=Dog - description of dog which is in race",
  "PSD_Dog_name=Name of dog",
  "PSD_Dog_breed=Breed of dog (from list which is create in separate window)",
  "PSD_Dog_genderType=Gender of dog",
  "PSD_Dog_licenceNumber=License number of dog",
  "PSD_Dog_birthdate=Birthdate of dog",
  "PSD_Dog_comment=Any comment which is need for this dog"
})
public class DogImplBeanInfo extends SimpleBeanInfo {

  private static final Logger LOG = LoggerFactory.getLogger(DogImplBeanInfo.class);

  public static final String[] SHOWN_PROPERTIES = new String[] {"name", "breed", "genderType",
    "licenceNumber", "birthdate", "comment", "id", "modifier", "modified"};

  private static final Set<String> EDITABLE_PROPERTIES = new HashSet<>(
          Arrays.asList(new String[] {"name", "breed", "genderType", "licenceNumber", "birthdate", "comment"}));
  private static final Set<String> READONLY_PROPERTIES = new HashSet<>(
          Arrays.asList(new String[] {"id", "modifier", "modified"}));

  @Override
  public BeanDescriptor getBeanDescriptor() {
    BeanDescriptor bd = new BeanDescriptor(DogImpl.class, null);
    bd.setDisplayName(Bundle.DN_Dog());
    bd.setShortDescription(Bundle.SD_Dog());
    return bd;
  }

  public static String propertyDisplayName(String property) {
    if ("id".equals(property) || "modifier".equals(property) || "modified".equals(property)) { // FIXME change this implementation
      return NbBundle.getMessage(DogImplBeanInfo.class, "PDN_DTO_" + property);
    } else {
      return NbBundle.getMessage(DogImplBeanInfo.class, "PDN_Dog_" + property);
    }
  }

  public static String propertyShortDescription(String property) {
if ("id".equals(property) || "modifier".equals(property) || "modified".equals(property)) { // FIXME change this implementation
      return NbBundle.getMessage(DogImplBeanInfo.class, "PSD_DTO_" + property);
    } else {
      return NbBundle.getMessage(DogImplBeanInfo.class, "PSD_Dog_" + property);
    }
  }

  @Override
  public PropertyDescriptor[] getPropertyDescriptors() {
    PropertyDescriptor[] result = super.getPropertyDescriptors();
    if (result == null) {
      try {
        result = new PropertyDescriptor[] {
          new PropertyDescriptor("id", DogImpl.class, "getId", null),
          new PropertyDescriptor("modified", DogImpl.class, "getModified", null),
          new PropertyDescriptor("modifier", DogImpl.class, "getModifier", null),
          new PropertyDescriptor("name", DogImpl.class, "getName", "setName"),
          new PropertyDescriptor("breed", DogImpl.class, "getBreed", "setBreed"),
          new PropertyDescriptor("genderType", DogImpl.class, "getGenderType", "setGenderType"),
          new PropertyDescriptor("licenceNumber", DogImpl.class, "getLicenceNumber", "setLicenceNumber"),
          new PropertyDescriptor("birthdate", DogImpl.class, "getBirthdate", "setBirthdate"),
          new PropertyDescriptor("comment", DogImpl.class, "getComment", "setComment")
        };
      } catch (IntrospectionException ex) {
        LOG.error("Faild defined property descriptors", ex);
        return result;
      }
    }
    LOG.debug("The result isn't null");

    for (PropertyDescriptor pd : result) {
      pd.setHidden(false);
      pd.setDisplayName(propertyDisplayName(pd.getName()));
      pd.setShortDescription(propertyShortDescription(pd.getName()));
    }
    return result;
  }
}
