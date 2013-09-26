/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.thecave.passcontrolserver.util;

/**
 *
 * @author guilherme
 */
public class UserPermission 
{
    public final static UserPermission ADMIN_PERMISSION_MASK   = new UserPermission(1);
    public final static UserPermission BALCONY_PERMISSION_MASK = new UserPermission(2);
    public final static UserPermission VIEWER_PERMISSION_MASK  = new UserPermission(4);
    public final static UserPermission PUSHER_PERMISSION_MASK  = new UserPermission(8);
    public final static UserPermission POPPER_PERMISSION_MASK  = new UserPermission(16);
    public final static UserPermission ALL_PERMISSION_MASK  = new UserPermission(63); //SOmatório anterior
        
    private int permissionCode = 0;       
    
    public UserPermission()
    {
        this.permissionCode = 0;
    }

    public UserPermission(int permissionCode)
    {
        this.permissionCode = permissionCode;
    }

    public int getPermissionCode() {
        return permissionCode;
    }
    
    public boolean hasAdminPermission()
    {
        return (permissionCode & ADMIN_PERMISSION_MASK.permissionCode) == ADMIN_PERMISSION_MASK.permissionCode;
    }
    public boolean hasBalconyPermission()
    {
        return (permissionCode & BALCONY_PERMISSION_MASK.permissionCode) == BALCONY_PERMISSION_MASK.permissionCode;
    }
    public boolean hasViewerPermission()
    {
        return (permissionCode & VIEWER_PERMISSION_MASK.permissionCode) == VIEWER_PERMISSION_MASK.permissionCode;
    }
    public boolean hasPusherPermission()
    {
        return (permissionCode & PUSHER_PERMISSION_MASK.permissionCode) == PUSHER_PERMISSION_MASK.permissionCode;
    }
    public boolean hasPopperPermission()
    {
        return (permissionCode & POPPER_PERMISSION_MASK.permissionCode) == POPPER_PERMISSION_MASK.permissionCode;
    } 
    public UserPermission addPermission(UserPermission newPermission)
    {
        permissionCode = (permissionCode | newPermission.permissionCode);
        return this;
    }
    
}
