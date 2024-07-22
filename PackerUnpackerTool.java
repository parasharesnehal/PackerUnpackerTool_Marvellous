
import java.util.*;
import java.io.*;
class PackerUnpackerTool
{
    

    public static void main(String Arg[]) throws Exception
    {
        Scanner sobj = new Scanner(System.in);

        System.out.println("-----------------------------------------------------");
        System.out.println("------- Marvellous Packer Unpacker CUI Module -------");
        System.out.println("-----------------------------------------------------");

        while(true)
        { 

            System.out.println("Enter your choice : ");
            int choice = sobj.nextInt();

        switch(choice)
        {
            

            case 1:
                Packerfinal.main(null);
                displayMenu();
                break;

            case 2:
                unpackerfinal.main(null);
                displayMenu();
                break;

            case 3:
                System.out.println("Thank you for using Marvellous Packer Unpacker tool");
                break;

            default:
                System.out.println("Invalid choice");
                break;
        }
        if(choice == 3)
        {
            break;
        }
    }
}
private static void displayMenu()
{
    System.out.println("1. Packing");
    System.out.println("2. Unpacking");
    System.out.println("3. Exit");
}


    class Packerfinal
    {
        public static void main(String Arg[]) throws Exception
        {
            Scanner spobj = new Scanner(System.in);


            System.out.println("----------------- Packing Activity ------------------");
            System.out.println();

            System.out.println("Enter the name of Directory that you want to open for packing : ");
            String FolderName = spobj.nextLine();

            File fpobj = new File(FolderName);

            System.out.println("Enter the name of packed file that you want to create : ");
            String PackedFile = spobj.nextLine();

            File Packobj = new File(PackedFile);
            
            boolean bret = Packobj.createNewFile();
            if(bret == false)
            {
                System.out.println("Unable to create packed file");
                return;
            }

            FileOutputStream foobj = new FileOutputStream(Packobj);

            if(fpobj.exists())
            {
                int i = 0, j = 0;
                int iCount = 0;

                File Arr[] = fpobj.listFiles();

                
                String PHeader = null;
                int iRet = 0;
                byte PBuffer[] = new byte[1024];
                FileInputStream fiobj = null;
                
                for(i = 0; i < Arr.length; i++)
                {
                    PHeader = Arr[i].getName();
                    
                    if(PHeader.endsWith(".txt"))
                    {
                        System.out.println("File packed with name : "+PHeader);
                        
                        PHeader = PHeader + " " + Arr[i].length();

                        for(j = PHeader.length(); j < 100; j++)
                        {
                            PHeader = PHeader + " ";
                        }

                        foobj.write(PHeader.getBytes(),0,100);

                        fiobj = new FileInputStream(Arr[i]);

                        while((iRet = fiobj.read(PBuffer))!= -1)
                        {
                            foobj.write(PBuffer,0,iRet);
                        }

                        fiobj.close();
                        iCount++;
                    }
                }

                System.out.println("-----------------------------------------------------");
                System.out.println("Packing activity completed..");
                System.out.println("Number of files scan : "+Arr.length);
                System.out.println("Number of files packed : "+iCount);
                System.out.println("-----------------------------------------------------");

                System.out.println("Thank you for using Marvellous Packer Unpacker tool");
                foobj.close();
            }
            else
            {
                System.out.println("There is no such directory");
            }
        
        }
    }


    class unpackerfinal
    {
        public static void main(String Arg[]) throws Exception
        {
            Scanner suobj = new Scanner(System.in);
            
            byte UHeader[] = new byte[100];
            int iResult = 0;
            String HeaderX = null;
            File obj = null;
            int FileSize = 0;
            FileOutputStream fuoobj = null;
            int iCount = 0;
            
            

            System.out.println("---------------- Unpacking Activity -----------------");
            System.out.println();

            System.out.println("Enter the name of Packed that you want to open : ");
            String PackedFile = suobj.nextLine();

            File fuobj = new File(PackedFile);

            if(!fuobj.exists())
            {
                System.out.println("Unable to proceed as Packed file is missing...");
                return;
            }  

            FileInputStream fuiobj = new FileInputStream(fuobj);

            while((iResult = fuiobj.read(UHeader,0,100)) > 0)
            {
                HeaderX = new String(UHeader);
                HeaderX = HeaderX.trim();

                String Tokens[] = HeaderX.split(" ");

                obj = new File(Tokens[0]);
                System.out.println("File drop with name : "+Tokens[0]);

                obj.createNewFile();

                FileSize = Integer.parseInt(Tokens[1]);
                byte UBuffer[] = new byte[FileSize];

                fuiobj.read(UBuffer,0,FileSize);

                fuoobj = new FileOutputStream(obj);
                fuoobj.write(UBuffer,0,FileSize);

                fuoobj.close();
                iCount++;
            }

            System.out.println("-----------------------------------------------------");
            System.out.println("Unpacking activity completed..");
            System.out.println("Number of files unpacked : "+iCount);
            System.out.println("-----------------------------------------------------");

            System.out.println("Thank you for using Marvellous Packer Unpacker tool");
            
            fuiobj.close();
        }
    }
}
