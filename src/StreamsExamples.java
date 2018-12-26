import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class StreamsExamples {

    public static void justRed() {
        FileInputStream fis = null;
        try {
            //  fis = new FileInputStream("text/test.txt");
            fis = new FileInputStream("C:\\Users\\Java Core Student 1\\IdeaProjects\\Lesson17\\text\\test.txt");
            System.out.println("file size in bytes : " + fis.available());
            int content;
            while ((content = fis.read()) != -1) {
                System.out.println((char) content);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    public static void reasWithResources() {
        try (FileInputStream fis = new FileInputStream("text/test.txt")) {
            System.out.println("file size " + fis.available());
            int content;
            while ((content = fis.read()) != -1) {
                System.out.println((char) content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readAndWrite() {
        try (FileInputStream fis = new FileInputStream("text/test.txt");
             FileOutputStream fos = new FileOutputStream("text/result.txt")) {
            int content;
            while ((content = fis.read()) != -1) {
                System.out.print((char) content);
                fos.write(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readAndWriteWithClosing() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("text/test.txt");
            fos = new FileOutputStream("text/result.txt");
            int content;
            while ((content = fis.read()) != -1) {
                System.out.println((char) content);
                fos.write(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void bufferedInputStream() {
        InputStream inStream = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bus = null;
        try {
            inStream = new FileInputStream("text/test.txt");
            bis = new BufferedInputStream(inStream);
            bus = new BufferedOutputStream(new FileOutputStream("text/buff_res.txt"));
            while (bis.available() > 0) {
                char c = (char) bis.read();
                System.out.println("Char : " + c);
                bus.write(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inStream != null && bis != null && bus != null) {
                try {
                    inStream.close();
                    bis.close();
                    bus.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void bufferedInputStreamWithResources(){
        try(FileInputStream fis  = new FileInputStream("test/test.txt");
            BufferedInputStream bus = new BufferedInputStream(fis);
            FileOutputStream fos = new FileOutputStream("test/buf_res.txt");
            BufferedOutputStream bos = new BufferedOutputStream(fos)){
            while (bus.available() > 0){
                char c = (char) bus.read();
                System.out.println("char :" + c );
                bos.write(c);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static List<String> getLinesFromFile(String filePath){
        File file = new File(filePath);
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = br.readLine())!= null){
                result.add(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }

    public static void writeByLines(){
        List<String> strings = new ArrayList<>();
        strings.add("One");
        strings.add("Two");
        strings.add("Three");
        Buffer writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("text/test.txt"));
            for (String s : strings){
                Writer.write (s);
            }
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (writer!= null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // justRed();
        //reasWithResources();
        //readAndWrite();
        //readAndWriteWithClosing();
        //bufferedInputStream();
        //bufferedInputStreamWithResources();

        List<String> file = getLinesFromFile("text/test.txt");
        for (String s : file){
            System.out.println(s);
        }
    }
}
