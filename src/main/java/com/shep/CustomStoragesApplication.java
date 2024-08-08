package com.shep;

import com.shep.storages.CustomArrayList;
import com.shep.storages.CustomHashSet;

import java.util.Iterator;

public class CustomStoragesApplication
{
    public static void main( String[] args )
    {
        //CustomArrayList demonstration
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("TestOne");
        list.add("TestTwo");
        System.out.println("ArrayList:");
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        list.remove(0);
        System.out.println(list.get(0));
        System.out.println("Size: " + list.size());

        // Custom HashSet demonstration
        CustomHashSet<String> set = new CustomHashSet<>();
        set.add("TestHashOne");
        set.add("TestHashTwo");
        System.out.println("\nHashSet:");
        System.out.println(set.contains("TestHashOne"));
        System.out.println(set.contains("TestHashTwo"));
        set.remove("TestHashOne");
        System.out.println(set.contains("TestHashOne"));

        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("Size: " + set.size());
    }
}
