package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GroupModel {
    private String groupName;
    private int groupId;
    private int userId;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    private boolean isAdmin;
    private Connection con;

    private GroupModel(int userId, int groupId, String groupName, boolean isAdmin) {

        this.userId = userId;
        this.groupId = groupId;
        this.groupName = groupName;
        this.isAdmin = isAdmin;
    }

    public GroupModel(){try {
        this.con = ConnectionManager.getConnection();

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }}

    public ArrayList<GroupModel> getGroupsList(int userId) {
        try {
            String query = "SELECT agroups.*, groupmembers.group_id, groupmembers.isAdmin " +
                    "FROM agroups " +
                    "JOIN groupmembers ON agroups.id = groupmembers.group_id " +
                    "WHERE groupmembers.member_id = ?";

            PreparedStatement selectGroupsPreparedStatement = con.prepareStatement(query);
            selectGroupsPreparedStatement.setInt(1, userId);
            ResultSet result = selectGroupsPreparedStatement.executeQuery();
            ArrayList<GroupModel> listOfGroups = new ArrayList<>();
            while (result.next()) {
                String groupName = result.getString("gName");
                int groupId = result.getInt("group_id");
                boolean isAdmin = result.getBoolean("isAdmin");
                GroupModel groupModel = new GroupModel(userId, groupId, groupName, isAdmin);
                listOfGroups.add(groupModel);
            }
            return listOfGroups;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean createGroup(String GroupName){
        try {
            if (!GroupName.isBlank()) {
                String aGroupsQuery = "insert into agroups (gName) values (?)";
                PreparedStatement agroupsPreparedStatement = con.prepareStatement(aGroupsQuery);
                agroupsPreparedStatement.setString(1, GroupName);
                int aGroupsResult = agroupsPreparedStatement.executeUpdate();

                if (aGroupsResult == 1){
                    String getIdQuery = "SELECT LAST_INSERT_ID()";
                    ResultSet getIdResult =
                            con.prepareStatement(getIdQuery).executeQuery();
                    if (getIdResult.next()){
                        int id = getIdResult.getInt("LAST_INSERT_ID()");
                        String groupMembersQuery = String.format(
                                "insert into groupmembers (group_id, member_Id, isAdmin) values ('%d', '%d', true)",
                                id, this.userId
                        );
                        int groupMembersQueryResult = con.prepareStatement(groupMembersQuery).executeUpdate();
                        if (groupMembersQueryResult == 1) {
                            System.out.println("Group Created");
                            return true;
                        }
                        else
                        {
                            System.out.println("Group not created");
                        }
                    }
                }
            }
        }
        catch (SQLException e){
            System.out.println("ERROR");
        }
        catch (NullPointerException ignored){}
        return false;
    }
}