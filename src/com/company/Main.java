package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    /**
     *start test of classes: NoteBook and WriteNote
     *
     * @throws BufferedReader exceptions
     */
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

    /**
     * This class NoteBook contains and manages all notes in ArrayList
     */

    public static class NoteBook {

        /**
         * Declare new oject  ArrayList<WriteNote>
         */

        private ArrayList<WriteNote> allNotes;

        /**
         * Create new object allNotes
         */

        private NoteBook() {
            allNotes = new ArrayList<>();
        }

        /**
         *
         * @param newNote add new note in notebook
         */

        private void addNote(WriteNote newNote) {
            allNotes.add(newNote);
        }

        /**
         *
         * @param deleteNote delete note from notebook
         */

        private void deleteNote(WriteNote deleteNote){
            allNotes.remove(deleteNote);
        }

        /**
         *
         * @throws IOException exception by BufferedReader
         */

        private void editNote() throws IOException {
            InputStream inputStream = System.in;
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            System.out.println("what line do u want to edit? (count starts from zero)");

            /**
             * Check - is entered number in diapason of out ArrayList
             * and if yes - editing it
             * if not - repeat entering
             */

            int num = Integer.parseInt(bufferedReader.readLine());
            while (num > allNotes.size()){
                System.out.println("current elemtent not in list. try another");
                num = Integer.parseInt(bufferedReader.readLine());
            }

            allNotes.get(num).rewriteNote(num);
        }

        /**
         * show all notes in object notebook
         */

        private void showAllNotes(){
            for (WriteNote note: allNotes) {
                System.out.println(note.toString());
            }
        }
    }

    /**
     * Class to create new note in notebook
     */

    public static class WriteNote {

        private String message = " - changed!!!!";
        private static int numberOfNote = 1;
        private String nameOfNote = "Test Note ";
        private String noteContent = " bla-bla-bla ";

        /**
         * Constructor without arguments
         */

        public WriteNote() {
            this.nameOfNote = nameOfNote + numberOfNote;
            numberOfNote++;
        }

        /**
         * constructor with arguments
         * @param nameOfNote not default name of note in notebook
         */

        public WriteNote(String nameOfNote){
            setName(nameOfNote);
            numberOfNote++;
        }

        /**
         * create new note with default content
         * @return defalt content in note
         */

        private WriteNote writeContent(){
            this.noteContent = noteContent;
            return this;
        }

        /**
         * create new note with entering content
         * @param noteContent entering content
         * @return new note with not default content
         */

        private WriteNote writeContent(String noteContent){
            this.noteContent = noteContent;
            return this;
        }

        /**
         *set default name to object writeNote
         * @param nameOfNote name of note
         */

        private void setName(String nameOfNote){
            this.nameOfNote = nameOfNote + numberOfNote;
            numberOfNote++;
        }

        /**
         * uses to change note content
         * @param message
         * @return new message
         */

        private String logMessage(String message){
            return this.message += message;

        }

        /**
         * rewrite note
         * @param noteNum number of note to rewrite
          */

        private void rewriteNote(int noteNum){
            this.noteContent = noteContent + message;
            logMessage(String.valueOf(System.out.printf("u have changed %d note \n", noteNum)));
        }

        /**
         * return name of object
         * @return name of object
         */

        private String getName(){
            return this.nameOfNote;
        }

        /**
         *show all notes in console
         * @return note in String
         */

        @Override
        public String toString() {
            return " Note -" + getName() + " - " + getNoteContent();
        }

        /**
         *get note content
         * @return note content
         */

        public String getNoteContent() {
            return this.noteContent;
        }
    }


}
