package foro.hub.api.controller;

import foro.hub.api.topico.DatosActualizarTopico;
import foro.hub.api.topico.DatosListadoTopico;
import foro.hub.api.topico.DatosRegistroTopico;
import foro.hub.api.topico.Topico;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public void RegistrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {
        Topico topico = new Topico(datosRegistroTopico);
        System.out.println("Topico antes de guardar: " + topico);
        topicoRepository.save(topico);
    }

    @GetMapping
    public Page<DatosListadoTopico> listadoTopicos(@PageableDefault(size=10) Pageable paginacion) {
//        return topicoRepository.findAll(paginacion).map(DatosListadoTopico::new);
        return topicoRepository.findByActivoTrue(paginacion).map(DatosListadoTopico::new);
    }

    @GetMapping("/{id}")
    public DatosListadoTopico listadoTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        return new DatosListadoTopico(topico);
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Topico> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isPresent()) {
            Topico topico = topicoOptional.get();
            topico.actualizarDatos(datosActualizarTopico);
            return ResponseEntity.ok(topico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
