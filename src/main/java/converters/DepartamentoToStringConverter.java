package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Departamento;

@Component
@Transactional
public class DepartamentoToStringConverter implements Converter<Departamento, String> {
	
	@Override
	public String convert(Departamento departamento) {
		String result;

		if (departamento == null)
			result = null;
		else
			result = String.valueOf(departamento.getId());

		return result;
	}

}
