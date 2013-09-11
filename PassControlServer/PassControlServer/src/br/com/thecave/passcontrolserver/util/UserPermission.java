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
    private final static int ADMIN_PERMISSION_MASK = 1;
    private final static int BALCONY_PERMISSION_MASK = 2;
    private final static int VIEWER_PERMISSION_MASK = 4;
    private final static int PUSHER_PERMISSION_MASK = 8;
    private final static int POPPER_PERMISSION_MASK = 16;
    private final static int CONFIG_PERMISSION_MASK = 32;
    
    public static boolean hasAdminPermission(int permissionCode)
    {
        return (permissionCode & ADMIN_PERMISSION_MASK) == ADMIN_PERMISSION_MASK;
    }
    public static boolean hasBalconyPermission(int permissionCode)
    {
        return (permissionCode & BALCONY_PERMISSION_MASK) == BALCONY_PERMISSION_MASK;
    }
    public static boolean hasViewerPermission(int permissionCode)
    {
        return (permissionCode & VIEWER_PERMISSION_MASK) == VIEWER_PERMISSION_MASK;
    }
    public static boolean hasPusherPermission(int permissionCode)
    {
        return (permissionCode & PUSHER_PERMISSION_MASK) == PUSHER_PERMISSION_MASK;
    }
    public static boolean hasPopperPermission(int permissionCode)
    {
        return (permissionCode & POPPER_PERMISSION_MASK) == POPPER_PERMISSION_MASK;
    }
    public static boolean hasConfigPermission(int permissionCode)
    {
        return (permissionCode & CONFIG_PERMISSION_MASK) == CONFIG_PERMISSION_MASK;
    }
  
    public static int getAdminPermissionMask()
    {
        return ADMIN_PERMISSION_MASK;
    }
    public static int getBalconyPermissionMask()
    {
        return BALCONY_PERMISSION_MASK;
    }
    public static int getViewerPermissionMask()
    {
        return VIEWER_PERMISSION_MASK;
    }
    public static int getPusherPermissionMask()
    {
        return PUSHER_PERMISSION_MASK;
    }
    public static int getPopperPermissionMask()
    {
        return POPPER_PERMISSION_MASK;
    }
    public static int getConfigPermissionMask()
    {
        return CONFIG_PERMISSION_MASK;
    }
    public static int getAllPermissionMask()
    {
        return ADMIN_PERMISSION_MASK | BALCONY_PERMISSION_MASK | VIEWER_PERMISSION_MASK | PUSHER_PERMISSION_MASK | POPPER_PERMISSION_MASK | CONFIG_PERMISSION_MASK;
    }
    
}
