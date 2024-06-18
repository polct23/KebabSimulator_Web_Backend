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
    public static String createQuerySELECTWithTwoColumns(Class theClass, String column1, String column2) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM ").append(theClass.getSimpleName())
                .append(" WHERE ").append(column1).append(" = ? AND ")
                .append(column2).append(" = ?");
        return query.toString();
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

    public static String createQueryForPlayerAbilities() {
        return "SELECT a.idAbility, a.abilityName, a.description, a.value, a.price FROM Ability a " +
                "JOIN playersability pa ON a.idAbility = pa.idAbility " +
                "JOIN player p ON pa.idPlayer = p.idPlayer " +
                "WHERE p.idPlayer = ?";
    }
}
