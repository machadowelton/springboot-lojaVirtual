package br.com.loja_virtual.controllers.handling;

import br.com.loja_virtual.domain.exceptions.ObjetoNaoEncontradoException;
import br.com.loja_virtual.domain.exceptions.RegraNegocioException;
import br.com.loja_virtual.domain.exceptions.RespostaCampoErro;
import br.com.loja_virtual.domain.exceptions.RespostaErro;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@ControllerAdvice
public class ControleExcecaoRestController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<RespostaErro> responderViolacaoRegraNegocio(RegraNegocioException ex, WebRequest request) {
        RespostaErro respostaErro =
                RespostaErro.builder()
                        .mensagem(ex.getMessage())
                        .dataExecucao(LocalDateTime.now().toString())
                        .path(request.getDescription(false).split("=")[1])
                        .codigoErroHttp(HttpStatus.BAD_REQUEST.value())
                        .fraseErroHttp(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .build();
        return new ResponseEntity<>(respostaErro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ObjetoNaoEncontradoException.class)
    public ResponseEntity<RespostaErro> responderObjetoNaoEncontrado(ObjetoNaoEncontradoException ex, WebRequest request) {
        RespostaErro respostaErro =
                RespostaErro.builder()
                        .mensagem(ex.getMessage())
                        .dataExecucao(LocalDateTime.now().toString())
                        .path(request.getDescription(false).split("=")[1])
                        .codigoErroHttp(HttpStatus.NOT_FOUND.value())
                        .fraseErroHttp(HttpStatus.NOT_FOUND.getReasonPhrase())
                        .build();
        return new ResponseEntity<>(respostaErro, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final String endpointNaoEncontrado = "O endpoint solicitado não existe ou foi movido ou está indisponivel";
        RespostaErro respostaErro =
                RespostaErro.builder()
                        .mensagem(endpointNaoEncontrado)
                        .dataExecucao(LocalDateTime.now().toString())
                        .path(request.getDescription(false).split("=")[1])
                        .codigoErroHttp(HttpStatus.NOT_FOUND.value())
                        .fraseErroHttp(HttpStatus.NOT_FOUND.getReasonPhrase())
                        .build();
        return new ResponseEntity<Object>(respostaErro, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, List<String>> camposComErro = new HashMap<>();
        ex.getBindingResult().getAllErrors().stream().forEach((erros) -> {
            String nomeCampo = ((FieldError) erros).getField();
            String mensagemErro = erros.getDefaultMessage();
            if (camposComErro.containsKey(nomeCampo)) {
                List<String> errosCampo = camposComErro.get(nomeCampo);
                errosCampo.add(mensagemErro);
                camposComErro.remove(nomeCampo);
                camposComErro.put(nomeCampo, errosCampo);
            } else {
                camposComErro.put(nomeCampo, Arrays.asList(mensagemErro));
            }
        });
        RespostaCampoErro respostaCampoErro =
                new RespostaCampoErro("Alguns campos não passaram na validação",
                        LocalDateTime.now().toString(), request.getDescription(false).split("=")[1],
                        HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST.value(), camposComErro);
        return new ResponseEntity<Object>(respostaCampoErro, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespostaErro> responderErroInteroOuNaoTratado(Exception ex, WebRequest request) {
        RespostaErro respostaErro =
                RespostaErro.builder()
                        .mensagem("Ocorreu um erro ao processar a requisição")
                        .dataExecucao(LocalDateTime.now().toString())
                        .path(request.getDescription(false).split("=")[1])
                        .codigoErroHttp(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .fraseErroHttp(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                        .build();
        return new ResponseEntity<>(respostaErro, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
