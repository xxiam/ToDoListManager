package org.example;
import java.util.HashMap;
import java.util.Optional;

public class Handler {
    
    protected JSONReader jsonReader;
    protected HashMap<String, Entry> entryMap;

    public Handler() {
        this.jsonReader = new JSONReader();
        this.entryMap = jsonReader.readJSON();
    }

    public void manualSave() {
        jsonReader.writeJSON(entryMap);
    }

    public static String createDate() {
        java.time.LocalDate today = java.time.LocalDate.now(); 
        return today.toString();
    }

    // methods to add, edit, delete, and view entries

    // add entry, takes in title, description, priority, dateCreated, dateDue (optional)
    public void addEntry(String title, String description, int priority, String dateDue) {
        String dateCreated = createDate();
        Entry entry = new Entry(title, description, priority, dateCreated, dateDue);
        if (entryMap.containsKey(title)) {
            throw new IllegalArgumentException("Entry with this title already exists");
        }
        entryMap.put(entry.title, entry);
        jsonReader.writeJSON(entryMap);
    }

    public Entry removeEntry(String title) {
        Entry entry = entryMap.remove(title);
        jsonReader.writeJSON(entryMap);
        return entry;
    }

    public void editEntry(String title, String newTitle, String newDescription, int newPriority, String newDateDue) {
        Entry entry = getEntry(title);
        if (entry == null) {
            throw new IllegalArgumentException("Entry not found");
        } else if (entry != null) {
            entry.setTitle(newTitle);
            entry.setDescription(newDescription);
            entry.setPriority(newPriority);
            entry.setDateDue(newDateDue);
            jsonReader.writeJSON(entryMap);
        }
    }

    public void markEntryAsCompleted(String title) {
        Entry entry = entryMap.get(title);
        if (entry != null) {
            entry.setCompleted(true);
            entry.setDateCompleted(createDate());
            jsonReader.writeJSON(entryMap);
        }
    }

    public Entry getEntry(String title) {
        Entry entry = entryMap.get(title);
        if (entry == null) {
            throw new IllegalArgumentException("Entry not found");
        }
        return entry;
    }

    public HashMap<String, Entry> viewAllEntries(Optional <Integer> filterType) {
        HashMap<String, Entry> filteredEntries = new HashMap<>();
        
        if (filterType.isPresent()) {
            /*
             * filter :
             * 1 - completed
             * 2 - not completed
             * 3 - sort by priority
             * 4 - sort by date created
             * 5 - sort by date due
             */
            int filter = filterType.get();

            switch (filter) {
                
                case 1:
                    for (HashMap.Entry<String, Entry> entry : entryMap.entrySet()) {
                        if (entry.getValue().completed) {
                            filteredEntries.put(entry.getKey(), entry.getValue());
                        }
                    }
                    break;
                case 2:
                    for (HashMap.Entry<String, Entry> entry : entryMap.entrySet()) {
                        if (!entry.getValue().completed) {
                            filteredEntries.put(entry.getKey(), entry.getValue());
                        }
                    }
                    break;
                case 3:
                    // sort by priority and add to filteredEntries
                    entryMap.entrySet().stream()
                        .sorted((e1, e2) -> Integer.compare(e1.getValue().priority, e2.getValue().priority))
                        .forEach(entry -> filteredEntries.put(entry.getKey(), entry.getValue()));
                    
                    break;
                case 4:
                    // sort by date created
                    entryMap.entrySet().stream()
                        .sorted((e1, e2) -> e1.getValue().dateCreated.compareTo(e2.getValue().dateCreated))
                        .forEach(entry -> filteredEntries.put(entry.getKey(), entry.getValue()));
                    break;
                case 5:
                    // sort by date due
                    entryMap.entrySet().stream()
                        .sorted((e1, e2) -> e1.getValue().dateDue.compareTo(e2.getValue().dateDue))
                        .forEach(entry -> filteredEntries.put(entry.getKey(), entry.getValue()));
                    break;
                default:
                    break;
            }
        } else { // if no filter is provided, print all entries
            return entryMap;
        }
        return filteredEntries;
    }
}
