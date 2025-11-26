# Solução para o Erro 403 ao Criar Matéria

## Problema Identificado

O erro **403 Forbidden** ocorre ao tentar criar uma matéria através do endpoint `/api/materias/criar` no Swagger porque existe uma **configuração conflitante** no ficheiro `SecurityConfiguration.java`.

### Análise do Problema

O endpoint `/api/materias/criar` aparece em **duas listas diferentes** com requisitos de autorização conflitantes:

1. **Linha 47** - Em `ENDPOINTS_ADMINISTRADOR`: requer autoridade `ADMINISTRADOR`
2. **Linha 55** - Em `ENDPOINTS_USUARIO`: requer autoridade `USUARIO`

No entanto, na configuração do `SecurityFilterChain` (linhas 85-86), as regras são processadas **sequencialmente**:

```java
.requestMatchers(ENDPOINTS_ADMINISTRADOR).hasAuthority("ADMINISTRADOR")  // Linha 85
.requestMatchers(ENDPOINTS_USUARIO).hasAuthority("USUARIO")              // Linha 86
```

Como a regra de `ENDPOINTS_ADMINISTRADOR` é avaliada **primeiro**, o Spring Security aplica apenas essa restrição. Isto significa que:

- ✅ Utilizadores com role **ADMINISTRADOR** conseguem criar matérias
- ❌ Utilizadores com role **USUARIO** recebem erro **403 Forbidden**

A segunda regra (linha 86) nunca é aplicada ao endpoint `/api/materias/criar` porque ele já foi "capturado" pela primeira regra.

---

## Solução

Existem **três abordagens** para resolver este problema:

### Opção 1: Criar Lista Partilhada (Recomendada)

Criar uma nova lista `ENDPOINTS_USUARIO_E_ADMINISTRADOR` para endpoints que devem ser acessíveis por ambas as roles, e usar `hasAnyAuthority()`.

**Vantagens:**
- Clara separação de responsabilidades
- Fácil de manter e expandir
- Evita duplicação de endpoints

**Implementação:**

```java
// Remover "/api/materias/criar" de ENDPOINTS_ADMINISTRADOR e ENDPOINTS_USUARIO

// Criar nova lista
public static final String [] ENDPOINTS_USUARIO_E_ADMINISTRADOR = {
    "/api/materias/criar"
};

// No SecurityFilterChain, adicionar ANTES das regras específicas:
.requestMatchers(ENDPOINTS_USUARIO_E_ADMINISTRADOR).hasAnyAuthority("USUARIO", "ADMINISTRADOR")
.requestMatchers(ENDPOINTS_ADMINISTRADOR).hasAuthority("ADMINISTRADOR")
.requestMatchers(ENDPOINTS_USUARIO).hasAuthority("USUARIO")
```

### Opção 2: Regra Específica no SecurityFilterChain

Adicionar uma regra específica para o endpoint `/api/materias/criar` antes das regras gerais.

**Vantagens:**
- Solução rápida
- Não requer reorganização das listas

**Desvantagens:**
- Menos escalável para múltiplos endpoints partilhados

**Implementação:**

```java
// Remover "/api/materias/criar" de ENDPOINTS_ADMINISTRADOR e ENDPOINTS_USUARIO

// No SecurityFilterChain, adicionar:
.requestMatchers("/api/materias/criar").hasAnyAuthority("USUARIO", "ADMINISTRADOR")
.requestMatchers(ENDPOINTS_ADMINISTRADOR).hasAuthority("ADMINISTRADOR")
.requestMatchers(ENDPOINTS_USUARIO).hasAuthority("USUARIO")
```

### Opção 3: Remover de ENDPOINTS_ADMINISTRADOR

Manter o endpoint apenas em `ENDPOINTS_USUARIO`, assumindo que administradores também têm a autoridade `USUARIO`.

**Vantagens:**
- Mudança mínima no código

**Desvantagens:**
- Só funciona se administradores tiverem ambas as roles
- Pode causar confusão na arquitetura de segurança

**Implementação:**

```java
// Simplesmente remover "/api/materias/criar" de ENDPOINTS_ADMINISTRADOR
public static final String [] ENDPOINTS_ADMINISTRADOR = {
    "/api/usuarios/atualizar/{id}",
    "/api/usuarios/apagar/{id}",
    "/api/usuarios/{id}",
    "/api/usuarios/atualizar-status/{id}",
    "/api/usuarios/listar"
    // REMOVIDO: "/api/materias/criar"
};
```

---

## Implementação Recomendada (Opção 1)

Foi criado o ficheiro `SecurityConfiguration_CORRIGIDO.java` com a implementação da **Opção 1**, que é a mais robusta e escalável.

### Passos para Aplicar a Correção

1. **Fazer backup** do ficheiro original:
   ```bash
   cp src/main/java/com/Aprova/demo/config/SecurityConfiguration.java src/main/java/com/Aprova/demo/config/SecurityConfiguration.java.backup
   ```

2. **Substituir** o ficheiro original pelo corrigido:
   ```bash
   cp SecurityConfiguration_CORRIGIDO.java src/main/java/com/Aprova/demo/config/SecurityConfiguration.java
   ```

3. **Recompilar** a aplicação:
   ```bash
   mvn clean compile
   ```

4. **Reiniciar** a aplicação

5. **Testar** no Swagger:
   - Fazer login com um utilizador com role `USUARIO`
   - Tentar criar uma matéria através do endpoint `/api/materias/criar`
   - Verificar que não ocorre mais o erro 403

---

## Verificação Adicional

Caso o problema persista após aplicar a correção, verifique:

1. **Token JWT**: Certifique-se de que está a usar um token válido no Swagger
   - Clicar no botão "Authorize" no Swagger UI
   - Inserir o token no formato: `Bearer {seu_token_aqui}`

2. **Role do Utilizador**: Confirme que o utilizador tem a role correta na base de dados
   ```sql
   SELECT id, nome, email, role FROM usuarios WHERE email = 'seu_email@exemplo.com';
   ```

3. **Logs da Aplicação**: Verificar se há mensagens de erro relacionadas com autenticação/autorização

---

## Resumo das Alterações

### Ficheiro: `SecurityConfiguration.java`

**Removido:**
- `/api/materias/criar` de `ENDPOINTS_ADMINISTRADOR` (linha 47)
- `/api/materias/criar` de `ENDPOINTS_USUARIO` (linha 55)

**Adicionado:**
- Nova constante `ENDPOINTS_USUARIO_E_ADMINISTRADOR` contendo `/api/materias/criar`
- Nova regra no `SecurityFilterChain`: `.requestMatchers(ENDPOINTS_USUARIO_E_ADMINISTRADOR).hasAnyAuthority("USUARIO", "ADMINISTRADOR")`

**Ordem das Regras (importante):**
1. Endpoints públicos (sem autenticação)
2. Endpoints partilhados (USUARIO ou ADMINISTRADOR) ← **NOVO**
3. Endpoints exclusivos de ADMINISTRADOR
4. Endpoints exclusivos de USUARIO
5. Endpoints que requerem apenas autenticação
6. Negar tudo o resto

---

## Contacto

Se tiver dúvidas ou precisar de assistência adicional, não hesite em contactar.
