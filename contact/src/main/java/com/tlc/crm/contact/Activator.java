package com.tlc.crm.contact;

import com.tlc.crm.contact.api.Contact;
import com.tlc.crm.contact.api.ContactManager;
import com.tlc.validator.TlcModel;
import org.checkerframework.checker.units.qual.C;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author Abishek
 * @version 1.0
 */
public class Activator implements BundleActivator
{
    @Override
    public void start(BundleContext bundleContext)
    {
       // System.out.println("Testing CRUD ");
        //ContactManager.getInstance().create(new Contact());

    }

    @Override
    public void stop(BundleContext bundleContext)
    {

    }
}
