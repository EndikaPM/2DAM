public static void main(String[] args) {
    try {
        ServerSocket socket = new ServerSocket(7000);
        Socket cliente = socket.accept();


        BufferedReader lectura = new BufferedReader(new InputStreamReader(
                cliente.getInputStream()
        ));


        int numero = Integer.parseInt(lectura.readLine());
        int doble =numero * 2;

        BufferedWriter salida = new BufferedWriter(new OutputStreamWriter(
                cliente.getOutputStream()
        ));
        salida.write(doble);


    }catch(UnknownHostException e){


    }
    catch (IOException e){


    }
}
