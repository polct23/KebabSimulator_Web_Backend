package edu.upc.dsa.util;

public class QueryHelper {

    public static String createQueryINSERT(Object entity) {

        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("(");

        String [] fields = edu.upc.dsa.util.ObjectHelper.getFields(entity);
        String propToUppercase = entity.getClass().getName().substring(0, 1).toUpperCase() + entity.getClass().getName().substring(1);
        String columnName = "id" + propToUppercase;
      //  sb.append(columnName);
        sb.append(fields[0]);
        int primerField = 0;
        for (String field: fields) {
         //   if (!field.equals(columnName)) sb.append(", ").append(field);
            if(primerField == 0){
                primerField = 1;
            }
            else{
                sb.append(", ").append(field);
            }
        }
        sb.append(") VALUES (?");
        primerField = 0;
        for (String field: fields) {
            //if (!field.equals(columnName))  sb.append(", ?");
            if(primerField == 0){
                primerField = 1;
            }
            else{
                sb.append(", ?");
            }

        }
        sb.append(")");
        // INSERT INTO User (ID, lastName, firstName, address, city) VALUES (0, ?, ?, ?,?)
        return sb.toString();
    }

    /*public static String createQuerySELECT(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE ID = ?");

        return sb.toString();
    }
     */
    public static String createQuerySELECT(Class theClass, String columna) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());
        sb.append(" WHERE " + columna + " = ?");

        return sb.toString();
    }


    public static String createQuerySELECTall(Class theClass) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());

        return sb.toString();
    }

    public static String createQueryDELETE(Class theClass, String columna) {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(theClass.getSimpleName());
        sb.append(" WHERE " + columna + " = ?");

        return sb.toString();
    }

    public static String createQueryUPDATEPlayer(String columna) {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE Player ");
        sb.append("SET " + columna + " = ?");
        sb.append(" WHERE USERNAME = ?");

        return sb.toString();
    }
    public static String createQueryUPDATEPlayersAblity(String columna) {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE PlayersAbilities ");
        sb.append("SET " + columna + " = ?");
        sb.append(" WHERE USERNAME = ?");

        return sb.toString();
    }

    public String createQueryPlayerAbilities() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT p.userName, a.abilityName ")
                .append("FROM player p ")
                .append("JOIN player_ability pa ON p.idPlayer = ? ")
                .append("JOIN ability a ON pa.idAbility = ?");

        return sb.toString();
    }
}
