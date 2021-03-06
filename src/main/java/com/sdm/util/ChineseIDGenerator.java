package com.sdm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 说明：身份证号码生成器
 * Created by qinyun.
 * 2016/8/27 10:04
 */
public class ChineseIDGenerator {
    private static String[] EvenNumberString = {"0","2","4","6","8" };
    private static String[] OddNumberString = { "1", "3", "5", "7", "9" };
    private static String[] AreaCode001_200 = { "110101", "110102", "110103", "110104", "110105", "110106",
            "110107", "110108", "110109", "110111", "110112", "110113", "110114", "110115", "110116", "110117",
            "110228", "110229", "120101", "120102", "120103", "120104", "120105", "120106", "120107", "120108",
            "120109", "120110", "120111", "120112", "120113", "120114", "120115", "120221", "120223", "120225",
            "130101", "130102", "130103", "130104", "130105", "130107", "130108", "130121", "130123", "130124",
            "130125", "130126", "130127", "130128", "130129", "130130", "130131", "130132", "130133", "130181",
            "130182", "130183", "130184", "130185", "130201", "130202", "130203", "130204", "130205", "130207",
            "130208", "130223", "130224", "130225", "130227", "130229", "130230", "130281", "130283", "130301",
            "130302", "130303", "130304", "130321", "130322", "130323", "130324", "130401", "130402", "130403",
            "130404", "130406", "130421", "130423", "130424", "130425", "130426", "130427", "130428", "130429",
            "130430", "130431", "130432", "130433", "130434", "130435", "130481", "130501", "130502", "130503",
            "130521", "130522", "130523", "130524", "130525", "130526", "130527", "130528", "130529", "130530",
            "130531", "130532", "130533", "130534", "130535", "130581", "130582", "130601", "130602", "130603",
            "130604", "130621", "130622", "130623", "130624", "130625", "130626", "130627", "130628", "130629",
            "130630", "130631", "130632", "130633", "130634", "130635", "130636", "130637", "130638", "130681",
            "130682", "130683", "130684", "130700", "130701", "130702", "130703", "130705", "130706", "130721",
            "130722", "130723", "130724", "130725", "130726", "130727", "130728", "130729", "130730", "130731",
            "130732", "130733", "130801", "130802", "130803", "130804", "130821", "130822", "130823", "130824",
            "130825", "130826", "130827", "130828", "130901", "130902", "130903", "130921", "130922", "130923",
            "130924", "130925", "130926", "130927", "130928", "130929", "130930", "130981", "130982", "130983",
            "130984", "131001", "131002", "131003" };
    private static String[] AreaCode201_400 = { "131022", "131023", "131024", "131025", "131026", "131028",
            "131081", "131082", "131101", "131102", "131121", "131122", "131123", "131124", "131125", "131126",
            "131127", "131128", "131181", "131182", "140100", "140101", "140105", "140106", "140107", "140108",
            "140109", "140110", "140121", "140122", "140123", "140181", "140201", "140202", "140203", "140211",
            "140212", "140221", "140222", "140223", "140224", "140225", "140226", "140227", "140301", "140302",
            "140303", "140311", "140321", "140322", "140401", "140402", "140411", "140421", "140423", "140424",
            "140425", "140426", "140427", "140428", "140429", "140430", "140431", "140481", "140501", "140502",
            "140521", "140522", "140524", "140525", "140581", "140600", "140601", "140602", "140603", "140621",
            "140622", "140623", "140624", "140701", "140702", "140721", "140722", "140723", "140724", "140725",
            "140726", "140727", "140728", "140729", "140781", "140801", "140802", "140821", "140822", "140823",
            "140824", "140825", "140826", "140827", "140828", "140829", "140830", "140881", "140882", "140901",
            "140902", "140921", "140922", "140923", "140924", "140925", "140926", "140927", "140928", "140929",
            "140930", "140931", "140932", "140981", "141001", "141002", "141021", "141022", "141023", "141024",
            "141025", "141026", "141027", "141028", "141029", "141030", "141031", "141032", "141033", "141034",
            "141081", "141082", "141101", "141102", "141121", "141122", "141123", "141124", "141125", "141126",
            "141127", "141128", "141129", "141130", "141181", "141182", "150401", "150402", "150403", "150404",
            "150421", "150422", "150423", "150424", "150425", "150426", "150428", "150429", "150430", "150501",
            "150502", "150521", "150522", "150523", "150524", "150525", "150526", "150581", "150602", "150621",
            "150622", "150623", "150624", "150625", "150626", "150627", "150701", "150702", "150721", "150722",
            "150723", "150724", "150725", "150726", "150727", "150781", "150782", "150783", "150784", "150785",
            "150801", "150802", "150821", "150822" };
    private static String[] AreaCode401_600 = { "150823", "150824", "150825", "150826", "150901", "150902",
            "150921", "150922", "150923", "150924", "150925", "150926", "150927", "150928", "150929", "150981",
            "152201", "152202", "152221", "152222", "152223", "152224", "152501", "152502", "152522", "152523",
            "152524", "152525", "152526", "152527", "152528", "152529", "152530", "152531", "152600", "152900",
            "152921", "152922", "152923", "210101", "210102", "210103", "210104", "210105", "210106", "210111",
            "210112", "210113", "210114", "210122", "210123", "210124", "210181", "210200", "210201", "210202",
            "210203", "210204", "210211", "210212", "210213", "210224", "210281", "210282", "210283", "210301",
            "210302", "210303", "210304", "210311", "210321", "210323", "210381", "210401", "210402", "210403",
            "210404", "210411", "210421", "210422", "210423", "210501", "210502", "210503", "210504", "210505",
            "210521", "210522", "210601", "210602", "210603", "210604", "210624", "210681", "210682", "210701",
            "210702", "210703", "210711", "210726", "210727", "210781", "210782", "210801", "210802", "210803",
            "210804", "210811", "210881", "210882", "210901", "210902", "210903", "210904", "210905", "210911",
            "210921", "210922", "211001", "211002", "211003", "211004", "211005", "211011", "211021", "211081",
            "211101", "211102", "211103", "211121", "211122", "211201", "211202", "211204", "211221", "211223",
            "211224", "211281", "211282", "211301", "211302", "211303", "211321", "211322", "211324", "211381",
            "211382", "211401", "211402", "211403", "211404", "211421", "211422", "211481", "220000", "220100",
            "220101", "220102", "220103", "220104", "220105", "220106", "220112", "220122", "220181", "220182",
            "220183", "220200", "220201", "220202", "220203", "220204", "220211", "220221", "220281", "220282",
            "220283", "220284", "220300", "220301", "220302", "220303", "220322", "220323", "220381", "220382",
            "220400", "220401", "220402", "220403", "220421", "220422", "220500", "220501", "220502", "220503",
            "220521", "220523", "220524", "220581" };

    private static String[][] AreaCodes = { AreaCode001_200, AreaCode201_400, AreaCode401_600 };

    /**
     *
     * @param areaCode 区域码
     * @param birthday 生日
     * @param gender 性别，1-男，2-女
     * @return
     */
    public static String GenID(String areaCode, Date birthday, int gender)
    {
        String retValue = null;

        if (areaCode == null) areaCode = "100100";
        if (gender != 1) gender = 2;

        try
        {
            StringBuilder sb = new StringBuilder(20);
            Random rd = new Random();

            sb.append(areaCode);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

            sb.append(formatter.format(birthday));

            sb.append(rd.nextInt(89) + 10);

            if (gender == 1) sb.append(OddNumberString[rd.nextInt(5)]);
            else sb.append(EvenNumberString[rd.nextInt(5)]);

            String tempString = sb.toString();

            sb.append(ChineseID.calcIDChecksum(tempString));

            retValue = sb.toString();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return retValue;
    }

    /**
     * 随机生成身份证号码
     * @return
     */
    public static String genRandomID(){
        Random rd = new Random();
        int gender =  rd.nextInt(2) + 1;//随机性别
//        System.out.println("1.gender = " + gender);


        int year = rd.nextInt(10) + 1990;////随机生日-年
        int month = rd.nextInt(11);//月
        int day = rd.nextInt(27);//日

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
//        System.out.println("2.calendar = " + year + "-" + month + "-" + day);

//        System.out.println("name = " + ChineseUserNameGenerator.genRandName(gender));
        return genRandomID(calendar.getTime(), gender);
    }

    /**
     *
     * @param birthday 生日
     * @param gender 性别，1-男，2-女
     * @return
     */
    public static String genRandomID(Date birthday,int gender)
    {
        String retValue = null;

        try
        {
            StringBuilder sb = new StringBuilder(20);
            Random rd = new Random();

            int areaBlockCount = AreaCodes.length;

            int blockNo = rd.nextInt(areaBlockCount);
            int areaPosition = rd.nextInt(AreaCodes[blockNo].length);

            sb.append(AreaCodes[blockNo][areaPosition]);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            sb.append(formatter.format(birthday));

            sb.append(rd.nextInt(89) + 10);

            if (gender == 1) sb.append(OddNumberString[rd.nextInt(5)]);
            else sb.append(EvenNumberString[rd.nextInt(5)]);

            String tempString = sb.toString();

            sb.append(ChineseID.calcIDChecksum(tempString));

            retValue = sb.toString();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return retValue;
    }
    public static int IdNOToAge(String IdNO){
        int leh = IdNO.length();
        String y="";
        String m = "";
        if (leh == 18) {
            y = IdNO.substring(6, 10);
            m = IdNO.substring(10,12);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-mm");
            String year=df.format(new Date());
            int u=Integer.parseInt(year)-Integer.parseInt(y);
            return u;
        }else{
            y = IdNO.substring(6, 8);
            return Integer.parseInt(y);
        }

    }
    public static void main(String[] args) throws ParseException, Exception {
        for(int i = 0;i< 1000;i++) {
            System.out.println(genRandomID());
        }

//        Calendar calendar = Calendar.getInstance();
//        calendar.set(1989, 8, 18);
//
//        String id = genRandomID(calendar.getTime(), 1);
//        System.out.println("id = " + id);
    }
    
    
}
