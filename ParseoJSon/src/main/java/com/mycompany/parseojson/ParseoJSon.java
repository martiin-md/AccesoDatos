package com.mycompany.parseojson;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author martin
 */
public class ParseoJSon {

    public static void main(String[] args) throws IOException {
        File file = new File("generated.json");

        ObjectMapper objectMapper = new ObjectMapper();

        List<User> users = objectMapper.readValue(file, new TypeReference<List<User>>() {
        });

        // Menú 
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nMenú de Operaciones:");
            System.out.println("1. Editar usuario");
            System.out.println("2. Eliminar usuario");
            System.out.println("3. Mostrar usuarios");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    editarUsuario(users, scanner);
                    break;
                case 2:
                    eliminarUsuario(users, scanner);
                    break;
                case 3:
                    mostrarUsuarios(users);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

            // Guardar cambios en el archivo
            objectMapper.writeValue(file, users);
        }

        scanner.close();
    }

    // Método para editar un usuario
    private static void editarUsuario(List<User> users, Scanner scanner) {
        System.out.print("Ingrese el GUID del usuario a editar: ");
        String guid = scanner.nextLine();

        for (User user : users) {
            if (user.getGuid().equals(guid)) {
                System.out.println("Usuario encontrado: " + user.getName());
                System.out.print("Ingrese el nuevo balance: ");
                String nuevoBalance = scanner.nextLine();
                user.setBalance(nuevoBalance);
                System.out.println("Balance actualizado.");
                return;
            }
        }
        System.out.println("Usuario no encontrado.");
    }

    // Método para eliminar un usuario
    private static void eliminarUsuario(List<User> users, Scanner scanner) {
        System.out.print("Ingrese el GUID del usuario a eliminar: ");
        String guid = scanner.nextLine();

        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getGuid().equals(guid)) {
                iterator.remove();
                System.out.println("Usuario eliminado.");
                return;
            }
        }
        System.out.println("Usuario no encontrado.");
    }

    // Método para mostrar todos los usuarios
    private static void mostrarUsuarios(List<User> users) {
        for (User user : users) {
            System.out.println(user);
        }
    }
}

// Clase para representar el JSON
class User {

    private String _id;
    private int index;
    private String guid;

    // Esta anotación mapea el campo JSON "isActive" a la variable de clase "isActive"
    @JsonProperty("isActive")
    private boolean isActive;

    private String balance;
    private String picture;
    private int age;
    private String eyeColor;
    private String name;
    private String gender;
    private String company;
    private String email;
    private String phone;
    private String address;
    private String about;
    private String registered;
    private double latitude;
    private double longitude;
    private List<String> tags;
    private List<Friend> friends;
    private String greeting;
    private String favoriteFruit;

    // Getters y Setters
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getRegistered() {
        return registered;
    }

    public void setRegistered(String registered) {
        this.registered = registered;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getFavoriteFruit() {
        return favoriteFruit;
    }

    public void setFavoriteFruit(String favoriteFruit) {
        this.favoriteFruit = favoriteFruit;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", balance='" + balance + '\''
                + ", age=" + age
                + ", email='" + email + '\''
                + ", phone='" + phone + '\''
                + '}';
    }
}

// Clase para representar los amigos
class Friend {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
