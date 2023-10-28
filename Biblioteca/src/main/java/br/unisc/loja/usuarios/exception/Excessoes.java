package br.unisc.loja.usuarios.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class Excessoes {
    public static class AssociationNotFoundException extends RuntimeException {
        public AssociationNotFoundException(String msg) {
            super(msg);
        }}


    public static class BibliotecaDuplicadaException extends DataIntegrityViolationException {
        public BibliotecaDuplicadaException(String msg) {
            super(msg);
        }}
        public static class BibliotecaNotFoundException extends RuntimeException{
            public BibliotecaNotFoundException(String msg){
                super (msg);
            }}
            public static class BibliotecaVaziaException extends RuntimeException {
                public BibliotecaVaziaException(String msg) {
                    super(msg);
                }
            }

    public static class LivroDuplicadoException extends DataIntegrityViolationException {
        public LivroDuplicadoException(String msg) {
            super(msg);
        }
    }

    public static class LivroExistsException extends RuntimeException{
        public LivroExistsException(String msg) {
            super(msg);
        }
    }

    public static class LivroNotFoundException extends RuntimeException{
        public LivroNotFoundException(String msg) {
            super(msg);
        }
    }



    public class TituloNotFoundException extends RuntimeException{
        public TituloNotFoundException(String msg) {
            super(msg);
        }
    }


        }
