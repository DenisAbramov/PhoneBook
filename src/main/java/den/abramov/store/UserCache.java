package den.abramov.store;

import den.abramov.model.User;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserCache {

    private static final UserCache userCache = new UserCache();

    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();

    private UserCache() {}

    public static UserCache getInstance() {
        return userCache;
    }

    public Collection<User> value() {
        return users.values();
    }

    public void add(User user) {
        users.put(user.getId(), user);
    }

    public void delete(int id) {
        users.remove(id);
    }

    public User get(int id) {
        return users.get(id);
    }

    public Collection<User> search(String name)
    {
        Collection<User> us = users.values();
        Collection<User> rezult = new CopyOnWriteArrayList<>();
        for (User u : us) {
            if(u.getName().toLowerCase().contains(name.toLowerCase()))
            {
                rezult.add(u);
            }
        }
        return rezult;
    }

    public User getName(String name)
    {
        Collection<User> us = users.values();
        for (User u : us) {
            if(u.getName().equalsIgnoreCase(name))
                return u;
        }
        return null;
    }

    public User getNumber(String number)
    {
        Collection<User> us = users.values();
        for (User u : us) {
            if(u.getNumber().equalsIgnoreCase(number))
                return u;

        }
        return null;
    }

    public Collection<User> watchOne(User user)
    {
        Collection<User> rezult = new CopyOnWriteArrayList<>();
        rezult.add(user);
        return rezult;
    }
}
