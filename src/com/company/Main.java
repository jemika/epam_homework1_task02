package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        NoteBook noteBook = new NoteBook();

        WriteNote writeNote1 = new WriteNote().writeContent("111111");
        WriteNote writeNote2 = new WriteNote().writeContent();
        WriteNote writeNote3 = new WriteNote("name999").writeContent("333333");


        System.out.println("notebook after adding notes ");
        noteBook.addNote(writeNote1);
        noteBook.addNote(writeNote2);
        noteBook.addNote(writeNote3);
        noteBook.showAllNotes();

        System.out.println("notebook after deleting note 1 ");
        noteBook.deleteNote(writeNote1);
        noteBook.showAllNotes();

        System.out.println("notebook after editing note 2 ");
        noteBook.editNote();
        noteBook.showAllNotes();
    }

    public static class NoteBook {

        private ArrayList<WriteNote> allNotes;

        private NoteBook() {
            allNotes = new ArrayList<>();
        }

        private void addNote(WriteNote newNote) {
            allNotes.add(newNote);
        }

        private void deleteNote(WriteNote deleteNote){
            allNotes.remove(deleteNote);
        }

        private void editNote() throws IOException {
            InputStream inputStream = System.in;
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            System.out.println("what line do u want to edit? (count starts from zero)");

            int num = Integer.parseInt(bufferedReader.readLine());
            while (num > allNotes.size()){
                System.out.println("current elemtent not in list. try another");
                num = Integer.parseInt(bufferedReader.readLine());
            }

            allNotes.get(num).rewriteNote(num);
        }

        private void showAllNotes(){
            for (WriteNote note: allNotes) {
                System.out.println(note.toString());
            }
        }


    }

    public static class WriteNote {

        private String message = " - changed!!!!";
        private static int numberOfNote = 1;
        private String nameOfNote = "Test Note ";
        private String noteContent = " bla-bla-bla ";

        public WriteNote() {
            this.nameOfNote = nameOfNote + numberOfNote;
            numberOfNote++;
        }

        public WriteNote(String nameOfNote){
            setName(nameOfNote);
            numberOfNote++;
        }

        private WriteNote writeContent(){
            this.noteContent = noteContent;
            return this;
        }
        private WriteNote writeContent(String noteContent){
            this.noteContent = noteContent;
            return this;
        }

        private void setName(String nameOfNote){
            this.nameOfNote = nameOfNote + numberOfNote;
            numberOfNote++;
        }

        private String logMessage(String message){
            return this.message += message;

        }

        private void rewriteNote(int noteNum){
            this.noteContent = noteContent + message;
            logMessage(String.valueOf(System.out.printf("u have changed %d note \n", noteNum)));
        }

        private String getName(){
            return this.nameOfNote;
        }

        @Override
        public String toString() {
            return " Note -" + getName() + " - " + getNoteContent();
        }

        public String getNoteContent() {
            return this.noteContent;
        }
    }


}
