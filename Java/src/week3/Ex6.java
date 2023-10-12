package week3;

import java.util.ArrayList;
import java.util.Scanner;

public class Ex6 {

    public static void insertPerson(ArrayList<Person> persons, String child, String parent) {
        for (int i = 0; i < persons.size(); i++) {
            if (persons.get(i).getName().equals(parent)) {
                ArrayList<Person> children = persons.get(i).getChildren();

                boolean childIsExist = false;

                for (Person person : persons) {
                    if (person.getName().equals(child)) {
                        children.add(person);
                        childIsExist = true;
                    }
                }

                if (!childIsExist) {
                    Person newPerson = new Person(child);
                    children.add(newPerson);
                    persons.add(newPerson);
                }

                persons.get(i).setChildren(children);
                return;
            }
        }
        Person newPerson = new Person(parent);

        ArrayList<Person> children = newPerson.getChildren();

        boolean childIsExist = false;

        for (Person person : persons) {
            if (person.getName().equals(child)) {
                children.add(person);
                childIsExist = true;
            }
        }

        if (!childIsExist) {
            Person newChild = new Person(child);
            children.add(newChild);
            persons.add(newChild);
        }

        persons.add(newPerson);
    }

    public static class Person {
        private String name;
        private ArrayList<Person> children = new ArrayList<Person>();

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<Person> getChildren() {
            return children;
        }

        public void setChildren(ArrayList<Person> children) {
            this.children = children;
        }
    }

    public static int calDescendants(Person person) {
        int descendants = person.getChildren().size();

        for (Person child: person.getChildren()) {
            descendants += calDescendants(child);
        }

        return descendants;
    }

    public static int calGeneration(Person person) {
        int generation = person.getChildren().isEmpty() ? 0 : 1;

        int maxGeneration = 0;

        for (Person child: person.getChildren()) {
            if (calGeneration(child) > maxGeneration) {
                maxGeneration = calGeneration(child);
            }
        }

        generation += maxGeneration;

        return generation;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Person> persons = new ArrayList<Person>();

        while (true) {
            String child = scanner.next();

            if (child.equals("***")) {
                break;
            }

            String parent = scanner.next();

            insertPerson(persons, child, parent);
        }

        while (true) {
            String command = scanner.next();

            if (command.equals("***")) {
                break;
            }

            String name = scanner.next();

            if (command.equals("descendants")) {
                for (Person person: persons) {
                    if (person.getName().equals(name)) {
                        System.out.println(calDescendants(person));
                    }
                }
            }

            if (command.equals("generation")) {
                for (Person person: persons) {
                    if (person.getName().equals(name)) {
                        System.out.println(calGeneration(person));
                    }
                }
            }
        }
    }
}
