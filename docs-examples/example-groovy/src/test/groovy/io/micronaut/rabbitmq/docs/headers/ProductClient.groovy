package io.micronaut.rabbitmq.docs.headers

import io.micronaut.context.annotation.Requires
// tag::imports[]
import io.micronaut.messaging.annotation.Header
import io.micronaut.rabbitmq.annotation.Binding
import io.micronaut.rabbitmq.annotation.RabbitClient
import io.micronaut.rabbitmq.annotation.RabbitHeaders
// end::imports[]

@Requires(property = "spec.name", value = "HeadersSpec")
// tag::clazz[]
@RabbitClient
@Header(name = "x-product-sealed", value = "true") // <1>
@Header(name = "productSize", value = "large")
interface ProductClient {

    @Binding("product")
    @Header(name = "x-product-count", value = "10") // <2>
    @Header(name = "productSize", value = "small")
    void send(byte[] data)

    @Binding("product")
    void send(@Header String productSize, // <3>
              @Header("x-product-count") Long count,
              byte[] data)

    @Binding("product")
    void send(@RabbitHeaders Map<String, Object> headers, // <4>
              byte[] data)
}
// end::clazz[]
