/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package classes;

/**
 *
 * @author abuyog
 */
public class AutoCompleteDBLink
{
String query = "";
AutoTextComplete ac = null;
java.sql.Connection connection = null;

public AutoCompleteDBLink(AutoTextComplete atc, String query, java.sql.Connection con) {
    this.query = query;
    ac = atc;
    connection = con;
    populate();
}


public void populate() {
    try {
        java.sql.Statement st = connection.createStatement();
        java.sql.ResultSet rs = st.executeQuery(query);
        java.util.ArrayList<String> al = new java.util.ArrayList<String>();
            while(rs.next())
                //System.out.println(rs.getString(1));
                al.add(rs.getString(1));
                ac.setItems(al);
    } catch(Exception ex) {ex.printStackTrace();}
    }
}