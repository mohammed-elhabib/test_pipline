package dz.itinfinity.orderMS.Services.OthersServices;

import dz.itinfinity.orderMS.Entities.Enums.*;
import dz.itinfinity.orderMS.Exceptions.InputValidationException.*;

import lombok.experimental.Helper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class HelperService {

    private static final Logger log = LoggerFactory.getLogger(HelperService.class);
    public static final String dateFormat = "yyyy-MM-dd ";

    ////////////////////////////////////////////// customise some helpers services //////////////////////////////////////////////////

    public static boolean isNotBlankInput(Object valueInput,String nameInput){
        log.info("- Start, verify validation syntax if input is blank (empty) or null ...");

        String emptyBlank = "blank";

        if(valueInput==null){
            log.error("null value '" + valueInput + "' of " + nameInput);
            throw new InputNullPointException("In this request "+nameInput+" is null point input!!!");
        }

        if (valueInput.toString().isEmpty())
            emptyBlank = "empty";

        if(StringUtils.isBlank(valueInput.toString())) {
            log.error(" value is blank '" + valueInput + "' for " + nameInput);
            throw new InputEmptyValueException("In this request " + nameInput + " value is " + emptyBlank + " input!!!");
        }
        return true;
    }

    public static boolean isNumericValue(Object valueInput,String nameInput){
        log.info("- Start, verify validation syntax if input is numeric value ...");

        if (StringUtils.contains(valueInput.toString(),'.')||StringUtils.contains(valueInput.toString(),',')||StringUtils.contains(valueInput.toString(),'%')){
            valueInput = StringUtils.remove(valueInput.toString(),'.');
            valueInput = StringUtils.remove(valueInput.toString(),',');
            valueInput = StringUtils.remove(valueInput.toString(),'%');
        }

        if (!StringUtils.isNumericSpace(valueInput.toString())) {
            log.error("invalid numeric value '" + valueInput + "' for " + nameInput);
            throw new InputInvalidNumericDataException("In this request " + nameInput + " value: '" + valueInput + "' is invalid number input!!!");
        }
        return true;
    }

    public static boolean isValidPath(String valueInput,String nameInput){
        log.info("- Start, verify validation syntax if input is valid path for image or any type of file ...");

        String mimetype = new MimetypesFileTypeMap().getContentType(new File(valueInput));
        String type = mimetype.split("/")[0];

        if (!type.equalsIgnoreCase("image")) {
            log.error("invalid value '" + valueInput + "' of " + nameInput);
            throw new InputInvalidNumericDataException("In this request " + nameInput + " value: '" + valueInput + "' is invalid path image!!!");
        }
        return true;
    }

    public static boolean isValidSyntaxEmail(String email, String name) {
        log.info("- Start, verify validation syntax if input is valid email ...");

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+"[a-zA-Z0-9_+&*-]+)*@"+"(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);

        if(!pat.matcher(email).matches()) {
            log.error("invalid value '" + email + "' of " + name);
            throw new InputInvalidSyntaxException("In this request " + name + " electronic value: '" + email + "' is invalid syntax!!!");
        }
        return true;
    }

    public static boolean isValidSyntaxPhone(String phone, String name) {
        log.info("- Start, verify validation syntax if input is valid phone ...");

        Pattern p = Pattern.compile("^(213|0){1}[5-7]{1}\\d{8}$");
        Matcher m = p.matcher(phone);

        if(!m.find() || !m.group().equals(phone)) {
            log.error("invalid value '" + phone + "' of " + name);
            throw new InputInvalidSyntaxException("In this request " + name + " number value: '" + phone + "' is invalid syntax!!!");
        }
        return true;
    }

    public static boolean isValidSyntaxPostalCode(String postalcode, String name) {
        log.info("- Start, verify validation syntax if input is valid postal code (code zip) ...");

        Pattern p = Pattern.compile("[0-6]{1}[0-8]{1}[0-9]{3}");
        Matcher m = p.matcher(postalcode);

        if(!m.find() || !m.group().equals(postalcode)) {
            log.error("invalid value '" + postalcode + "' of " + name);
            throw new InputInvalidSyntaxException("In this request " + name + " value: '" + postalcode + "' is invalid syntax!!!");
        }
        return true;
    }

    public static boolean isValidSyntaxChoiceEnum(String valueInput, String[] myEnum, String nameInput){
        log.info("- Start, verify validation syntax if input is valid value choice of enum type ...");

        String enums ="{";

        for (int i = 0; i < myEnum.length; i++){

            if (myEnum[i].equalsIgnoreCase(valueInput))
                return true;

            enums =enums+"'"+myEnum[i]+"'";

            if (i< myEnum.length-1)
                enums =enums+",";
        }
        enums = enums+"}";

        log.error("invalid value choice '"+valueInput+"' of enum type "+nameInput);
        throw new InputInvalidSyntaxException("In this request the "+nameInput+" value : '"+valueInput+"' is invalid syntax, please make sure it must be equal one of those choices : "+enums);
    }

    public static String getChoiceEnum(String[] myEnum){
        log.info("- Start, verify validation syntax if input is valid value choice of enum type ...");

        String enums ="";

        for (int i = 0; i < myEnum.length; i++){

            enums =enums+""+myEnum[i];

            if (i< myEnum.length-1)
                enums =enums+",";
        }
    return enums;
    }

    public static String [] getChoiceEnumList(String myEnum){
        log.info("- Start, verify validation syntax if input is valid value choice of enum type ...");
        String [] enums = myEnum.split(",");
        return enums;
    }

    public static boolean isValidFormatDate(String valueInput, String nameInput) {
        log.info("- Start, verify validation syntax if input is valid date format (yyyy-MM-dd HH:mm:ss) ...");

        DateFormat sdf = new SimpleDateFormat(dateFormat);
        System.out.println(sdf);
        sdf.setLenient(false);
        Date date;

        try {
            date =  sdf.parse(valueInput);
        } catch (ParseException e) {
            log.error("invalid format date '"+valueInput+"' must be in format (yyyy-MM-dd)");
            throw new InputInvalidFormatDateException("In this request the "+nameInput+" value : '"+valueInput+"' is invalid format date, please make sure your format date is '"+dateFormat.toString()+"' !!!");
        }
        return true;
    }

    public static java.sql.Date getValidFormatDate(String date) {
        log.info("- Start, for get valid Date format (yyyy-MM-dd HH:mm:ss) from string input ...");

        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
      //  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       // Date curDate = new Date(System.currentTimeMillis());
      //  String str = sdf.format(date);
        sdf.setLenient(true);

        try {
            System.out.println(sdf.parse(date) +" hhhhh");
            return convertUtilToSql(sdf.parse(date));

        } catch (ParseException e) {
            log.error("cannot get valide date object because this format date '"+date+"' is invalid !!!");
            throw new InputInvalidFormatDateException("This date '"+date+"' is invalid, cannot parse date that has not this format: '"+dateFormat.toString()+"' !!!");
        }
    }

    public static java.sql.Date convertUtilToSql(Date date) {
        log.info("- Start, convert date from Utils.Date to Sql.Date ...");

        java.sql.Date Date = new java.sql.Date(date.getTime());
        System.out.println(date.getTime() +" hhhhh");

        return Date;
    }

    public static Object getPaymentMethodChoices(Object enumClass, List<String> valueInput) {
        log.info("- Start, for get payment methods items of enum type from list string input choose ...");

        List<PaymentMethod> listChoices = new ArrayList<>();
        for (int i =0; i < valueInput.size(); i++){
            listChoices.add((PaymentMethod) getEnumItemChoice("paymentMethod", valueInput.get(i)));
        }
        EnumSet<PaymentMethod> setEnumChoices = EnumSet.copyOf(listChoices);
        return setEnumChoices;
    }

    public static Object getEnumItemChoice(String enumClass, String valueInput){
        log.info("- Start, for get item of enum type from string input choose ...");

       // String typeClass = StringUtils.remove(enumClass.toString(),"class com.saharaggab.ordermicroservice.Entities.Enums.");

        switch (enumClass) {
            case "BuyerStatus":
                if(valueInput.toString().equalsIgnoreCase("enterprise"))
                    return BuyerStatus.enterprise;
                if(valueInput.toString().equalsIgnoreCase("person"))
                    return BuyerStatus.person;

            case "EnterpriseStatus":
                if(valueInput.toString().equalsIgnoreCase("active"))
                    return EnterpriseStatus.active;
                if(valueInput.toString().equalsIgnoreCase("notActive"))
                    return EnterpriseStatus.noActive;

            case "OrderStatus":
                if(valueInput.toString().equalsIgnoreCase("initial"))
                    return OrderStatus.initial;
                if(valueInput.toString().equalsIgnoreCase("notConfirm"))
                    return OrderStatus.notConfirm;
                if(valueInput.toString().equalsIgnoreCase("reservationRequest"))
                    return OrderStatus.reservationRequest;
                if(valueInput.toString().equalsIgnoreCase("reserved"))
                    return OrderStatus.reserved;
                if(valueInput.toString().equalsIgnoreCase("cancel"))
                    return OrderStatus.cancel;
                if(valueInput.toString().equalsIgnoreCase("paid"))
                    return OrderStatus.paid;

            case "PublicPrivate":
                if(valueInput.toString().equalsIgnoreCase("Public"))
                    return PublicPrivate.Public;
                if(valueInput.toString().equalsIgnoreCase("Private"))
                    return PublicPrivate.Private;

            case "PaymentMethod":
                if(valueInput.toString().equalsIgnoreCase("virement"))
                    return PaymentMethod.virement;
                if(valueInput.toString().equalsIgnoreCase("bancaire"))
                    return PaymentMethod.bancaire;
                if(valueInput.toString().equalsIgnoreCase("edhahabia"))
                    return PaymentMethod.edhahabia;
                if(valueInput.toString().equalsIgnoreCase("facility"))
                return PaymentMethod.facility;

            default:
                log.error("invalid syntax input '"+valueInput+"' for enum type: "+enumClass);
                throw new InputInvalidSyntaxException("In this class enum '"+enumClass.toString()+"' for input value : '"+valueInput+"' is invalid syntax, please make sure it must be equal one of this class choices : ");
        }
    }

    public static boolean calculDelay(int delay, Date d){

        Date now = new Date();
        Date h = new Date((3600*delay)*1000);
        Date dateFinal = new Date(d.getTime() + h.getTime());
        if (now.compareTo(dateFinal)>=0){
            return true;
            //not confirm
        }
        else {
            return false;

        }
    }
}
